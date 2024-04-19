CREATE TABLE users (
    id SERIAL PRIMARY KEY,
	username VARCHAR(250),
    password VARCHAR(50)
);

CREATE TABLE devices (
    id SERIAL PRIMARY KEY,
    max_consumption float,
	min_consumption float,
	avg_consumption float,
	serial_no_device int,
	user_id int,
	CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE coordinates (
    id SERIAL PRIMARY KEY,
    longitude float,
	latitude float,
	speed float,
	timestapm timestamp,
	user_id int,
	device_id int,
	CONSTRAINT device_fk FOREIGN KEY (device_id) REFERENCES devices(id)
);