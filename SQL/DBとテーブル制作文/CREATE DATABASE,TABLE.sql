CREATE DATABASE ITP;

CREATE TABLE users (
    user_num INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(30) NOT NULL,
    password VARCHAR(20) NOT NULL,
    user_name VARCHAR(10) NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE questions (
    questions_id INT PRIMARY KEY,
    questions_text VARCHAR(40) NOT NULL
);

CREATE TABLE choices (
    choices_id INT PRIMARY KEY,
    questions_id INT NOT NULL,
    choices_text VARCHAR(15) NOT NULL,
    is_correct BOOLEAN NOT NULL,
    FOREIGN KEY (questions_id) REFERENCES questions(questions_id)
);

CREATE TABLE answers (
    answers_id INT PRIMARY KEY AUTO_INCREMENT,
    user_num INT NOT NULL,
    questions_id INT NOT NULL,
    answer_text VARCHAR(15) NOT NULL,
    answered_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    answers_correct BOOLEAN NOT NULL,
    FOREIGN KEY (user_num) REFERENCES users(user_num),
    FOREIGN KEY (questions_id) REFERENCES questions(questions_id)
);

CREATE TABLE results (
    results_id INT PRIMARY KEY AUTO_INCREMENT,
    user_num INT NOT NULL,
    results_score INT NOT NULL,
    FOREIGN KEY (user_num) REFERENCES users(user_num)
);

ALTER TABLE results　ADD COLUMN results_date TIMESTAMP DEFAULT　CURRENT_TIMESTAMP;