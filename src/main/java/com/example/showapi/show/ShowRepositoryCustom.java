package com.example.showapi.show;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.showapi.show.domain.Show;

public interface ShowRepositoryCustom {

	public Page<Show> findByDate(Pageable paging, Date dateFrom, Date dateTo, Float priceFrom, Float priceTo,
			String sortBy, String order);
	
}
