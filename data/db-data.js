db = db.getSiblingDB("showapi");
db.shows.drop();
db.shows.insertMany([
    {
        "_id": "1", "name": "shown number one", "duration": 7200, "description": "Some meaningful description about shown number one", "cast": [ "actor one", "actress one", "actor two" ],
        "plays": [
            {
                "_id": 1,
                "schedule": new Date("2020-11-15T15:00:00Z"),
                "theaterId": 1,
                "roomId": 2,
                "prices": [
                    { "sectionId": 1, "price": 100.0 },
                    { "sectionId": 2, "price": 90.0 }
                ]
            },
            {
                "_id": 2,
                "schedule": new Date("2020-11-15T20:00:00Z"),
                "theaterId": 1,
                "roomId": 1,
                "prices": [
                    { "sectionId": 3, "price": 100.0 },
                    { "sectionId": 4, "price": 90.0 },
                    { "sectionId": 5, "price": 70.0 }
                ]
            }
        ]
    },
    {
        "_id": "2", "name": "shown number two", "duration": 3600, "description": "Some meaningful description about shown number two", "cast": [ "actor one", "actress one", "actor two" ],
        "plays": [
            {
                "_id": 1,
                "schedule": new Date("2020-11-15T14:00:00Z"),
                "theaterId": 1,
                "roomId": 1,
                "prices": [
                    { "sectionId": 6, "price": 100.0 },
                    { "sectionId": 7, "price": 90.0 }
                ]
            }
        ]
    },
    {
        "_id": "3", "name": "shown number three", "duration": 5400, "description": "Some meaningful description about shown number three", "cast": [ "actor one", "actress one", "actor two" ],
        "plays": [
            {
                "_id": 1,
                "schedule": new Date("2020-11-15T15:00:00Z"),
                "theaterId": 2,
                "roomId": 1,
                "prices": [
                    { "sectionId": 8, "price": 100.0 },
                    { "sectionId": 9, "price": 90.0 }
                ]
            },
            {
                "_id": 2,
                "schedule": new Date("2020-11-15T20:00:00Z"),
                "theaterId": 2,
                "roomId": 2,
                "prices": [
                    { "sectionId": 10, "price": 100.0 }
                ]
            }
        ]
    },
    {
        "_id": "4", "name": "shown number four", "duration": 3600, "description": "Some meaningful description about shown number four", "cast": [ "actor one", "actress one", "actor two" ],
        "plays": [
            {
                "_id": 1,
                "schedule": new Date("2020-11-15T16:00:00Z"),
                "theaterId": 3,
                "roomId": 1,
                "prices": [
                    { "sectionId": 11, "price": 100.0 }
                ]
            }
        ]
    },
    {
        "_id": "5", "name": "shown number five", "duration": 7200, "description": "Some meaningful description about shown number five", "cast": [ "actor one", "actress one", "actor two" ],
        "plays": [
            {
                "_id": 1,
                "schedule": new Date("2020-11-15T20:00:00Z"),
                "theaterId": 2,
                "roomId": 1,
                "prices": [
                    { "sectionId": 12, "price": 100.0 },
                    { "sectionId": 13, "price": 90.0 }
                ]
            }
        ]
    }
]);

db.theaters.drop();
db.theaters.insertMany([
    {
        "_id": "1", "name": "Big Theater", "address": "Street 1 #123, north",
        "rooms": [
            { "_id": "1", "name": "Big Room", "capacity": 20 },
            { "_id": "2", "name": "Small Room", "capacity": 10 }
        ]
    },
    {
        "_id": "2", "name": "Medium Theater", "address": "Street 2 #456, south",
        "rooms": [
            { "_id": "1", "name": "Big Room", "capacity": 10 },
            { "_id": "2", "name": "Small Room", "capacity": 5 }
        ]
    },
    {
        "_id": "3", "name": "Small Theater", "address": "Street 3 #789, east",
        "rooms": [
            { "_id": "1", "name": "Big Room", "capacity": 10 }
        ]
    }
]);

db.sections.drop();
db.sections.insertMany([
    {
        "_id": 1,
        "seats": [
            { "number": 1, "available": true }, { "number": 2, "available": true }, { "number": 3, "available": true }, { "number": 4, "available": true }, { "number": 5, "available": true }
        ]
    },
    {
        "_id": 2,
        "seats": [
            { "number": 6, "available": true }, { "number": 7, "available": true }, { "number": 8, "available": true }, { "number": 9, "available": true }, { "number": 10, "available": true }
        ]
    },
    {
        "_id": 3,
        "seats": [
            { "number": 1, "available": true }, { "number": 2, "available": true }, { "number": 3, "available": true }, { "number": 4, "available": true }, { "number": 5, "available": true }
        ]
    },
    {
        "_id": 4,
        "seats": [
            { "number": 6, "available": true }, { "number": 7, "available": true }, { "number": 8, "available": true }, { "number": 9, "available": true }, { "number": 10, "available": true }
        ]
    },
    {
        "_id": 5,
        "seats": [
            { "number": 11, "available": true }, { "number": 12, "available": true }, { "number": 13, "available": true }, { "number": 14, "available": true }, { "number": 15, "available": true },
            { "number": 16, "available": true }, { "number": 17, "available": true }, { "number": 18, "available": true }, { "number": 19, "available": true }, { "number": 20, "available": true }
        ]
    },
    {
        "_id": 6,
        "seats": [
            { "number": 1, "available": true }, { "number": 2, "available": true }, { "number": 3, "available": true }, { "number": 4, "available": true }, { "number": 5, "available": true },
            { "number": 6, "available": true }, { "number": 7, "available": true }, { "number": 8, "available": true }, { "number": 9, "available": true }, { "number": 10, "available": true }
        ]
    },
    {
        "_id": 7,
        "seats": [
            { "number": 11, "available": true }, { "number": 12, "available": true }, { "number": 13, "available": true }, { "number": 14, "available": true }, { "number": 15, "available": true },
            { "number": 16, "available": true }, { "number": 17, "available": true }, { "number": 18, "available": true }, { "number": 19, "available": true }, { "number": 20, "available": true }
        ]
    },
    {
        "_id": 8,
        "seats": [
            { "number": 1, "available": true }, { "number": 2, "available": true }, { "number": 3, "available": true }, { "number": 4, "available": true }, { "number": 5, "available": true }
        ]
    },
    {
        "_id": 9,
        "seats": [
            { "number": 6, "available": true }, { "number": 7, "available": true }, { "number": 8, "available": true }, { "number": 9, "available": true }, { "number": 10, "available": true }
        ]
    },
    {
        "_id": 10,
        "seats": [
            { "number": 1, "available": true }, { "number": 2, "available": true }, { "number": 3, "available": true }, { "number": 4, "available": true }, { "number": 5, "available": true }
        ]
    },
    {
        "_id": 11,
        "seats": [
            { "number": 1, "available": true }, { "number": 2, "available": true }, { "number": 3, "available": true }, { "number": 4, "available": true }, { "number": 5, "available": true },
            { "number": 6, "available": true }, { "number": 7, "available": true }, { "number": 8, "available": true }, { "number": 9, "available": true }, { "number": 10, "available": true }
        ]
    }
]);
