CREATE TABLE users (
    user_id INT PRIMARY KEY,
    password VARCHAR(50)
);

CREATE TABLE devices (
    device_id INT PRIMARY KEY,
    max_consumption float,
	min_consumption float,
	avg_consumption float,
	serial_no_device int,
	user_id int,
	CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE coordinates (
    cooridnates_id INT PRIMARY KEY,
    longitude float,
	latitude float,
	speed float,
	timestapm timestamp,
	user_id int,
	device_id int,
	CONSTRAINT device_fk FOREIGN KEY (device_id) REFERENCES devices(device_id)
);
