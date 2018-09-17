-- 4.1 Задача: Показать название и описание коктейлей, которые опубликованы на сайте.
SELECT name, description 
FROM `cocktail`
WHERE status = 0;

-- 4.2 Задача:  Показать все комментарии к коктейлю «Ягодный коктейль». 
SELECT  text 
FROM `comment`
WHERE cocktail_id = (SELECT id 
					FROM `cocktail` 
					WHERE name = 'Ягодный коктейль');

-- 5.1 Задача: Показать список ингредиентов (в случайном порядке).                    
SELECT name 
FROM `ingredient` 
ORDER BY RAND();

-- 5.2 Задача: Показать список ингредиентов, но чтобы названия были в верхнем регистре.	
SELECT UCASE (name) 
FROM `ingredient`;
  
-- 6.1 Показать название, статус и описание коктейля, а также имя пользователя, создавшего его.
SELECT c.id, c.name, c.status, u.name, c.description 
FROM `cocktail` c 
JOIN `user` u ON c.user_id = u.id;


-- 6.2 Показать название ингредиента и коктейли, в которых он используется (если таковой есть).
SELECT i.id AS id_ingredient, 
		i.name AS name_ingredient, 
        c.name AS name_cocktal, 
        c.id AS id_cocktail
FROM `ingredient` i
LEFT JOIN `ingredient_cocktail` ic ON i.id = ic.ingredient_id
LEFT JOIN `cocktail` c ON ic.cocktail_id = c.id; 

-- 7.1 Показать название коктейля, средняя оценка которого больше 7.
SELECT c.id, c.name, AVG(mc.mark)
FROM `cocktail` c
JOIN `mark_cocktail` mc ON c.id = mc.cocktail_id
GROUP BY c.id
HAVING AVG(mark)>7;

-- 8.1 Показать все оценки, которые ставили пользователям и их коктейлям.
SELECT u.id, u.name, m.mark
FROM (SELECT mc.user_id, mc.mark
		FROM `mark_cocktail` mc
		UNION 
		SELECT mu.user_mark_id, mu.mark
		FROM `mark_user` mu) AS m
JOIN `user` u ON m.user_id = u.id;


-- 9.1 Показать всю информацию о коктейлях, который выложил пользователь (пользователи) под именем 'Lina'.
SELECT id, name, status, picture, description, recipe, user_id
FROM `cocktail`
WHERE user_id = (SELECT id
					FROM `user`
                    WHERE name = 'Lina');

-- 9.2 Показать пользователей, которые предложили больше одного коктейля.
SELECT u.id, u.name
FROM `user` u
WHERE 1< (SELECT COUNT(user_id)
			FROM `cocktail` c
            WHERE u.id = c.user_id AND c.status = 1);




