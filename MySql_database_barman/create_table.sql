-- MySQL Script generated by MySQL Workbench
-- Mon Aug 27 13:02:28 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema barman
-- -----------------------------------------------------
-- Помощник бармена. 
-- Существует перечень Коктейлей, за каждым из которых закреплен список Ингредиентов. 
-- Каждый Коктейль характеризуется еще названием, описанием рецепта приготовления (если есть необходимость), картинкой.
-- Ингредиенты в свою очередь характеризуются названием и мерой измерения продукта (килограммы, граммы, штука, столовая ложка и тд).
-- Бармен может составлять рецепты новых Коктейлей и предлагать их для оценки Клиентам.
-- Клиент может выставлять оценки Коктейлям и Барменам. Оценка осуществляется по 10-бальной шкале.  
-- Клиент может предлагать свои рецепты. 
-- Бармен может просмотреть список всех предложенных Коктейлей и опубликовать понравившейся от лица Клиента, предложившего его.
-- Пользователи системы могут оставлять Комментарии к Коктейлю. Также могут комментировать уже существующие Комментарии. 
-- Администратор может изменить статус Клиента на статус Бармена и обратно при наличии условий: число предложенных рецептов, высокие оценки других пользователей и наоборот. 
-- Администратор управляет Пользователями и контентом системы.
-- 

-- -----------------------------------------------------
-- Schema barman
--
-- Помощник бармена. 
-- Существует перечень Коктейлей, за каждым из которых закреплен список Ингредиентов. 
-- Каждый Коктейль характеризуется еще названием, описанием рецепта приготовления (если есть необходимость), картинкой.
-- Ингредиенты в свою очередь характеризуются названием и мерой измерения продукта (килограммы, граммы, штука, столовая ложка и тд).
-- Бармен может составлять рецепты новых Коктейлей и предлагать их для оценки Клиентам.
-- Клиент может выставлять оценки Коктейлям и Барменам. Оценка осуществляется по 10-бальной шкале.  
-- Клиент может предлагать свои рецепты. 
-- Бармен может просмотреть список всех предложенных Коктейлей и опубликовать понравившейся от лица Клиента, предложившего его.
-- Пользователи системы могут оставлять Комментарии к Коктейлю. Также могут комментировать уже существующие Комментарии. 
-- Администратор может изменить статус Клиента на статус Бармена и обратно при наличии условий: число предложенных рецептов, высокие оценки других пользователей и наоборот. 
-- Администратор управляет Пользователями и контентом системы.
-- 
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `barman` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `barman` ;

-- -----------------------------------------------------
-- Table `barman`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `barman`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'Сурогатный ключ.',
  `login` VARCHAR(50) NOT NULL COMMENT 'Логин пользователя для авторизации. Обязателен и уникален. ',
  `password` INT NOT NULL COMMENT 'Пароль пользователя, представлен в зашифрованом виде.',
  `role` TINYINT(2) NOT NULL COMMENT 'Роль пользователя в системе. Представлен в виде числа (0-Клиент, 1 - Бармен, 2 - Администратор).',
  `name` VARCHAR(150) NOT NULL COMMENT 'Имя пользователя в системе. Не уникальное.',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE)
ENGINE = InnoDB
COMMENT = 'Пользователь системы, который имеет свою роль в ней (0 - Клиент, 1 - Бармен, 2 - Администратор). Пользователь вводит логин и пароль для того, чтобы войти в систему. После входа, система отображает имя пользователя и его права.\n\nСтолбцы таблицы:\n- сурогатный ключ (id)\n- логин (login)\n- пароль (password)\n- роль (role)\n- имя пользователя (name)';


-- -----------------------------------------------------
-- Table `barman`.`Cocktail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `barman`.`Cocktail` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Сурогатный ключ.',
  `name` VARCHAR(150) NOT NULL COMMENT 'Название коктейля.',
  `description` VARCHAR(500) NULL COMMENT 'Описание коктейля. ',
  `status` TINYINT(2) NOT NULL COMMENT 'Статус коктейля (0 - Опубликован, 1 - Предложен, 2 - Архивирован).',
  `picture` MEDIUMBLOB NOT NULL COMMENT 'Картинка, прикрепленная к коктейлю. Если картинка не прикреплена, то загружается дефолтная картинка. ',
  `recipe` VARCHAR(1500) NOT NULL,
  `user_id` INT NOT NULL COMMENT 'Пользователь, создавший коктейль.',
  PRIMARY KEY (`id`),
  INDEX `fk_Cocktail_User1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_Cocktail_User`
    FOREIGN KEY (`user_id`)
    REFERENCES `barman`.`User` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Перечень коктейлей. Каждый коктейль имеет своё название и может иметь описание.  Бармен может создавать коктейли, а клиент - предлагать их. Для этого необходим статус коктейля, который отображает его состояние: опубликован, предложен, архивирован. К коктейлю может быть прикреплена картинка. Если таковой нет, то загружается картинка по умолчанию с сервера. У коктейля есть пользователь-автор, поэтому таблица ссылается на таблицу User.\n\nТаблица имеет столбцы:\n- сурогатный ключ (id)\n- имя (name)\n- описание (description)\n- статус: опубликован, архивирован, предложен (status)\n- картинку (picture)\n- ссылка на пользователя, создавшего коктейль (user_id)';


-- -----------------------------------------------------
-- Table `barman`.`Ingredient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `barman`.`Ingredient` (
  `id` SMALLINT NOT NULL AUTO_INCREMENT COMMENT 'Сурогатный ключ.',
  `name` VARCHAR(150) NOT NULL COMMENT 'Название ингредиента.',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
COMMENT = 'Ингредиенты, необходимые для создания коктеля. Имеют название.\n\nСтобцы таблицы:\n- сурогатный ключ (id) \n- название ингредиента (name)';


-- -----------------------------------------------------
-- Table `barman`.`Dimension`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `barman`.`Dimension` (
  `id` TINYINT NOT NULL AUTO_INCREMENT COMMENT 'Сурогатный ключ.',
  `dimension` VARCHAR(50) NOT NULL COMMENT 'Размерность ингредиента (кг, г, штуки, ст. л. и тд.).',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `dimension_UNIQUE` (`dimension` ASC) VISIBLE)
ENGINE = InnoDB
COMMENT = 'Каждый ингредиент имеет свою размерность: килограммы, граммы, штука, столовая ложка и тд. Таблица является списком размерностей и имеет два столбца:\n- сурогатный ключ (id)\n- название размерности (dimension)';


-- -----------------------------------------------------
-- Table `barman`.`Ingredient_Cocktail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `barman`.`Ingredient_Cocktail` (
  `cocktail_id` BIGINT NOT NULL COMMENT 'Коктейль для которого выбран ингредиент.',
  `ingredient_id` SMALLINT NOT NULL COMMENT 'Ингредиент для коктйля.',
  `count` FLOAT NOT NULL COMMENT 'Количество ингредиента в коктейле.',
  `dimension_id` TINYINT NOT NULL COMMENT 'Мера измерения ингредиента: граммы, килограммы, штука, столовая ложка',
  PRIMARY KEY (`cocktail_id`, `ingredient_id`),
  INDEX `fk_Ingredient_Cocktail_Cocktail1_idx` (`cocktail_id` ASC) VISIBLE,
  INDEX `fk_Ingredient_Cocktail_Ingredient1_idx` (`ingredient_id` ASC) VISIBLE,
  INDEX `fk_Ingredient_Cocktail_Dimension1_idx` (`dimension_id` ASC) VISIBLE,
  CONSTRAINT `fk_Ingredient_Cocktail_Cocktail1`
    FOREIGN KEY (`cocktail_id`)
    REFERENCES `barman`.`Cocktail` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Ingredient_Cocktail_Ingredient1`
    FOREIGN KEY (`ingredient_id`)
    REFERENCES `barman`.`Ingredient` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Ingredient_Cocktail_Dimension1`
    FOREIGN KEY (`dimension_id`)
    REFERENCES `barman`.`Dimension` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'За каждым Коктейлем закреплен перечень Ингредиентов. Таблица ссылается на таблицу Cocktail и Ingredient соответственно. Необходимо также знать количество конкретного ингредиента в коктейле и его меру измерения (штука, граммы, килограммы, столовая ложка).\n\nСтолбцы таблицы:\n- количество ингредиета (count)\n- ссылка на таблицу Cocktail, а именно на коктейль, для которого нужен конкретный ингредиент (cocktail_id)\n- ссылка на таблицу Ingredient, а именно на ингредиент, который необходим для коктейля (ingredient_id)\nпара cocktail_id и user_id - являются primasry_kay.\n- ссылка на таблицу Dimension для определения меры измерения. ';


-- -----------------------------------------------------
-- Table `barman`.`Comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `barman`.`Comment` (
  `child_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Сурогатный ключ. Является ключом дочернего комментария. ',
  `parent_id` BIGINT NULL COMMENT 'Родительский комментарий. Если родительского коментария нет, то значение будет NULL.',
  `text` VARCHAR(500) NOT NULL COMMENT 'Содержание комментария. ',
  `date` TIMESTAMP NOT NULL,
  `cocktail_id` BIGINT NOT NULL COMMENT 'Коктейль, к которому прикреплен комментарий.',
  `user_id` INT NOT NULL COMMENT 'Пользователь, который оставил комментарий.',
  PRIMARY KEY (`child_id`),
  INDEX `fk_Comment_Cocktail1_idx` (`cocktail_id` ASC) VISIBLE,
  INDEX `fk_Comment_Comment1_idx` (`parent_id` ASC) VISIBLE,
  INDEX `fk_Comment_User1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_Comment_Cocktail1`
    FOREIGN KEY (`cocktail_id`)
    REFERENCES `barman`.`Cocktail` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Comment_Comment1`
    FOREIGN KEY (`parent_id`)
    REFERENCES `barman`.`Comment` (`child_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Comment_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `barman`.`User` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Коментарии можно оставлять к коктейлю. Это может делать любой пользователь системы, независимо от его роли. Можно оставлять комментарии на комментарий. Для этого таблица имеет рекурсивную связь. \nСтолбцы таблицы:\n- сурогатный ключ, яляющийся ключом дочернего комментария (child_id)\n- ссылка на таблицу Comment, а имеено на родительский комментарий (parent_id)\n- содержание комментария (text)\n- ссылка на таблицу Cocktail, а имеено на коктейль, к которому ставится комментарий (cocktail_id)\n- ссылка на таблицу User, а именно на пользователя, который оставляет комментарий (user_id)';


-- -----------------------------------------------------
-- Table `barman`.`Mark_Cocktail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `barman`.`Mark_Cocktail` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Сурогатный ключ.',
  `mark` TINYINT NOT NULL COMMENT 'Отметка коктейлю пользователем по 10-бальной шкале.',
  `cocktail_id` BIGINT NOT NULL COMMENT 'Коктейль, которому ставят отметку.\nУникальный в паре с user_id.',
  `user_id` INT NOT NULL COMMENT 'Пользователь, который ставит отметку.\nУникальный в паре с cocktail_id.',
  INDEX `fk_Mark_Cocktail_Cocktail1_idx` (`cocktail_id` ASC) VISIBLE,
  INDEX `fk_Mark_Cocktail_User1_idx` (`user_id` ASC) INVISIBLE,
  PRIMARY KEY (`id`),
  UNIQUE (`cocktail_id`, `user_id`),
  CONSTRAINT `fk_Mark_Cocktail_Cocktail1`
    FOREIGN KEY (`cocktail_id`)
    REFERENCES `barman`.`Cocktail` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Mark_Cocktail_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `barman`.`User` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Клиент может ставить оценку коктейлю. Соответственно таблица ссылается на таблицы User и Cocktail. Клиент также может изменить оценку со временем. Но он не может ставить оценку повторно. Оценка ставится по 10-бальной шкале. \n\nТаблица имеет столбцы:\n- сурогатный ключ (id)\n- отметка (mark)\n- ссылка на таблицу Cocktail, которому выставляется оценка (cocktail_id)\n- ссылка на таблицу User, пользователь который выставляет оценка (user_id)\nПара coctail_id и user_id явлется уникальной.';


-- -----------------------------------------------------
-- Table `barman`.`Mark_User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `barman`.`Mark_User` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'Сурогатный ключ.',
  `mark` TINYINT(10) NOT NULL COMMENT 'Отметка пользователя пользователем по 10-ти бальной шкале.',
  `user_marked_id` INT NOT NULL COMMENT 'Пользователь, которому ставят отметку. \nУникальный в паре с user_mark_id.',
  `user_mark_id` INT NOT NULL COMMENT 'Пользователь, который ставит отметку.\nУникальный в паре с user_marked_id.',
  INDEX `fk_Mark_User_User2_idx` (`user_mark_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  UNIQUE (`user_marked_id`, `user_mark_id` ),
  CONSTRAINT `fk_Mark_User_User1`
    FOREIGN KEY (`user_marked_id`)
    REFERENCES `barman`.`User` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Mark_User_User2`
    FOREIGN KEY (`user_mark_id`)
    REFERENCES `barman`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Клиент может ставить оценку Бармену. Соответственно таблица ссылается на таблицу User. Клиент также может изменить оценку со временем. Но он не может ставить ее повторно. Оценка ставится по 10-бальной шкале. \n\nТаблица имеет столбцы:\n- сурогатный ключ (id)\n- оценка (mark)\n- ссылка на таблицу User, как пользователь для которого выставляют оценку (user_marked_id)\n- ссылка на таблицу User, как пользователя который выставляет оценку (user_mark_id)\nПара user_marked_id и user_mark_id являются уникальными.';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


