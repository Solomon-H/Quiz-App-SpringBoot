BEGIN;
DROP TABLE IF EXISTS quiz CASCADE;
DROP TABLE IF EXISTS question CASCADE;
DROP TABLE IF EXISTS quiz_question CASCADE;
DROP TABLE IF EXISTS quiz;
DROP TABLE IF EXISTS question;
DROP TABLE IF EXISTS quiz_question;

CREATE TABLE quiz (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255)
);

CREATE TABLE question (
    id SERIAL PRIMARY KEY,
    question VARCHAR(255),
    choice1 VARCHAR(255),
    choice2 VARCHAR(255),
    choice3 VARCHAR(255),
    answer VARCHAR(255),
    category VARCHAR(255)
);

CREATE TABLE quiz_question (
    quiz_id INT,
    question_id INT,
    PRIMARY KEY (quiz_id, question_id),
    FOREIGN KEY (quiz_id) REFERENCES quiz(id),
    FOREIGN KEY (question_id) REFERENCES question(id)
);

INSERT INTO question (question, choice1, choice2, choice3, answer, category) VALUES
    ('Who won the FIFA World Cup in 2018?', 'France', 'Germany', 'Brazil', 'France', 'Football'),
    ('Which player has the most goals in World Cup history?', 'Miroslav Klose', 'Pele', 'Lionel Messi', 'Miroslav Klose', 'Football'),
    ('What is the official ball used in FIFA World Cup 2022?', 'Adidas Tango', 'Nike Ordem', 'Puma Future Flash', 'Adidas Tango', 'Football');

INSERT INTO question (question, choice1, choice2, choice3, answer, category) VALUES
    ('Which NBA team has the most championships?', 'Los Angeles Lakers', 'Boston Celtics', 'Chicago Bulls', 'Boston Celtics', 'Basketball'),
    ('Who is the all-time leading scorer in the NBA?', 'LeBron James', 'Kareem Abdul-Jabbar', 'Kobe Bryant', 'Kareem Abdul-Jabbar', 'Basketball'),
    ('What is the regulation height of an NBA basketball hoop?', '9 feet', '10 feet', '11 feet', '10 feet', 'Basketball');

INSERT INTO question (question, choice1, choice2, choice3, answer, category) VALUES
    ('Which athlete holds the world record for the men 100m sprint?', 'Usain Bolt', 'Carl Lewis', 'Asafa Powell', 'Usain Bolt', 'Athletics'),
    ('What is the longest distance for a women marathon in the Olympics?', '26.2 miles', '13.1 miles', '50 kilometers', '26.2 miles', 'Athletics'),
    ('In which event did Jesse Owens win four gold medals at the 1936 Olympics?', '100m sprint', 'Long jump', 'Shot put', 'Long jump', 'Athletics');

COMMIT;