/*
 * Copyright (C) Intraway Corporation - All Rights Reserved (2015)
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 *
 * Proprietary and confidential
 *
 * This file is subject to the terms and conditions defined in file LICENSE.txt, which is part of this source code
 * package.
 */

package com.example.showapi;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FilenameUtils;
import org.awaitility.Awaitility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;

import de.flapdoodle.embed.mongo.MongoImportExecutable;
import de.flapdoodle.embed.mongo.MongoImportProcess;
import de.flapdoodle.embed.mongo.MongoImportStarter;
import de.flapdoodle.embed.mongo.config.IMongoImportConfig;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongoImportConfigBuilder;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

@TestConfiguration
@AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)
@AutoConfigureOrder(Ordered.LOWEST_PRECEDENCE)
@Profile("test")
public class MongoDataInitilializer implements ApplicationListener<ApplicationReadyEvent> {

	private static final byte[] IP4_LOOPBACK_ADDRESS = { 127, 0, 0, 1 };

	private static final byte[] IP6_LOOPBACK_ADDRESS = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };

	private Logger logger = LoggerFactory.getLogger(MongoDataInitilializer.class);

	@Autowired
	private ResourcePatternResolver resourceResolver;

	@Autowired
	private IMongodConfig mongodConfig;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		try {
			initMongoData();
		} catch (IOException e) {
			logger.error("Error initializing mongo test data.", e);
			throw new RuntimeException(e);
		}
	}

	@Bean
	public IMongodConfig embeddedMongoConfiguration() throws IOException {
		MongodConfigBuilder builder = new MongodConfigBuilder().version(Version.V4_0_2);

		builder.net(
				new Net(getHost().getHostAddress(), Network.getFreeServerPort(getHost()), Network.localhostIsIPv6()));

		return builder.build();
	}

	private InetAddress getHost() throws UnknownHostException {
		return InetAddress.getByAddress(Network.localhostIsIPv6() ? IP6_LOOPBACK_ADDRESS : IP4_LOOPBACK_ADDRESS);
	}

	private void initMongoData() throws IOException {
		logger.debug("Initializing test data");

		String database = "showapi";

		Resource[] resources = resourceResolver.getResources("classpath:/data/*.json");
		for (Resource resource : resources) {
			String collection = FilenameUtils.removeExtension(resource.getFilename());
			String file = resource.getFile().getAbsolutePath();
			logger.debug("Importing file {} to collection {}", resource.getFilename(), collection);

			MongoImportProcess mongoImport = startMongoImport(mongodConfig, database, collection, file, false, false,
					true);
			Awaitility.await().atMost(10, TimeUnit.SECONDS).until(() -> !mongoImport.isProcessRunning());
		}

		logger.debug("Test data initialized");
	}

	private MongoImportProcess startMongoImport(IMongodConfig mongodConfig, String dbName, String collection,
			String jsonFile, Boolean jsonArray, Boolean upsert, Boolean drop) throws IOException {
		IMongoImportConfig mongoImportConfig = new MongoImportConfigBuilder().version(mongodConfig.version())
				.net(new Net(mongodConfig.net().getPort(), mongodConfig.net().isIpv6())).db(dbName)
				.collection(collection).upsert(upsert).dropCollection(drop).jsonArray(jsonArray).importFile(jsonFile)
				.build();

		MongoImportExecutable mongoImportExecutable = MongoImportStarter.getDefaultInstance()
				.prepare(mongoImportConfig);
		MongoImportProcess mongoImport = mongoImportExecutable.start();

		return mongoImport;
	}

}
