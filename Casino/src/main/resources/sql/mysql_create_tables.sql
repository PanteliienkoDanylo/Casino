CREATE TABLE roles (
  roles_id    INT          NOT NULL,
  role_name   VARCHAR(20)  NOT NULL,
  description VARCHAR(200) NOT NULL,
  PRIMARY KEY (roles_id)
);

CREATE TABLE user (
  login_id        CHAR(20)   NOT NULL,
  password        VARCHAR(255)  NOT NULL,
  balance         DECIMAL(9, 2) NOT NULL,
  email           VARCHAR(30)   NOT NULL,
  last_login_date TIMESTAMP,
  PRIMARY KEY (login_id)
);

CREATE TABLE automat (
  automat_id   INT          NOT NULL,
  automat_name VARCHAR(20)  NOT NULL,
  description  VARCHAR(200) NOT NULL,
  PRIMARY KEY (automat_id)
);

CREATE TABLE user_data (
  passport   VARCHAR(20) NOT NULL,
  name       VARCHAR(30) NOT NULL,
  surname    VARCHAR(30) NOT NULL,
  patronymic VARCHAR(30),
  gender     BOOLEAN,
  birth_day  DATETIME    NOT NULL,
  country    VARCHAR(30),
  city       VARCHAR(30),
  address    VARCHAR(40),
  telephone  VARCHAR(25) NOT NULL,
  login_id   VARCHAR(30) NOT NULL,
  PRIMARY KEY (passport),
  FOREIGN KEY (login_id) REFERENCES user(login_id)
);

CREATE TABLE feedback (
  feedback_id  BIGINT       NOT NULL,
  message      VARCHAR(200) NOT NULL,
  email        VARCHAR(30)  NOT NULL,
  message_time TIMESTAMP    NOT NULL,

  PRIMARY KEY (feedback_id)
);

CREATE TABLE transaction (
  transaction_id BIGINT        NOT NULL,
  login_id       VARCHAR(20)   NOT NULL,
  date_time      TIMESTAMP     NOT NULL,
  amount         DECIMAL(9, 2) NOT NULL,
  info           VARCHAR(255),
  PRIMARY KEY (transaction_id),
  FOREIGN KEY (login_id) REFERENCES user (login_id)
    ON UPDATE CASCADE
);

CREATE TABLE games (
  game_id    BIGINT        NOT NULL,
  login_id   VARCHAR(20)   NOT NULL,
  automat_id INT           NOT NULL,
  amount     DECIMAL(9, 2) NOT NULL,
  date_time  TIMESTAMP,
  PRIMARY KEY (game_id),
  FOREIGN KEY (login_id) REFERENCES user (login_id)
    ON UPDATE CASCADE,
  FOREIGN KEY (automat_id) REFERENCES automat (automat_id)
    ON UPDATE CASCADE
);