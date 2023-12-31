
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
