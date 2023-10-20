
CREATE TABLE IF NOT EXISTS LINES (
   id INT AUTO_INCREMENT PRIMARY KEY,
   line_number VARCHAR(50) NOT NULL,
   default_transport VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS JOURNEY_PATTERN (
   id INT AUTO_INCREMENT PRIMARY KEY,
   line_number VARCHAR(50) NOT NULL,
   pattern_point VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS STOP_POINTS (
   id INT AUTO_INCREMENT PRIMARY KEY,
   stop_point VARCHAR(50) NOT NULL,
   stop_name VARCHAR(300) NOT NULL
);


--INSERT INTO LINES(line_number, default_transport) VALUES ("1", "BUS");
--INSERT INTO LINES(line_number, default_transport) VALUES ("2", "BUS");
--INSERT INTO LINES(line_number, default_transport) VALUES ("3", "BUS");


--INSERT INTO JOURNEY_PATTERN(line_number, pattern_point) VALUES ("3", "64028");
---INSERT INTO JOURNEY_PATTERN(line_number, pattern_point) VALUES ("3", "64030");


--INSERT INTO STOP_POINTS(stop_point, stop_name) VALUES ("64028", "test stop");
--INSERT INTO STOP_POINTS(stop_point, stop_name) VALUES ("64038", "test stop2");