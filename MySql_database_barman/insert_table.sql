INSERT INTO `barman`.`Dimension` (`id`, `dimension`) 
VALUES 
(1,'килограмм'),
(2,'грамм'),
(3,'столовая ложка'),
(4,'чайная ложка'),
(5,'штука'),
(6, 'литр'),
(7, 'миллилитр'),
(8, 'стакан'),
(9, 'банка');

INSERT INTO `barman`.`user` (`id`, `login`, `password`, `role`, `name`) 
VALUES 
('1','lena.gavrilova97', 11111111, 0, 'Elena'),
('2','vika.vasilieva', 22222222, 0, 'Vika'),
('3','micha.malevish', 33333333, 1, 'Micha'),
('4','roma.romanov', 44444444, 1, 'Roma'),
('5','lina.kruta', 55555555, 1, 'Lina'),
('6','dima.ageev', 66666666, 2, 'Dima');

INSERT INTO `barman`.`cocktail` (`id`, `name`, `status`, `picture`, `user_id`,`description`,`recipe`) 
VALUES 
(1,'Молочный коктейль с мороженым', 0, '',5, NULL,
'Берем бананы, очищаем от кожуры и нарезаем в чашу блендера. Сюда же добавляем, молоко и мороженное. Затем все хорошенько взбиваем на высокой скорости, примерно четыре минуты. Наливаем коктейль в стакан, приятного аппетита!'),
(2,'Ягодный коктейль', 0, '', 5, 'В сезон созревания ягод обязательно создайте вкусный и ароматный ягодный коктейль - такому напитку наверняка обрадуются не только дети, но и взрослые. Тем малышам, которые не любят употреблять молоко в чистом виде, понравится задумка и они с удовольствием опустошат стакан с таким молочным лакомством.',
'Подготовить ингредиенты: ягоды промыть, удалить с них хвостики или черенки. Если ягоды содержат крупные косточки, то извлечь их руками или специальным кухонным приспособлением. Всыпать очищенные и промытые ягоды в чашу блендера. Всыпать сахарный песок. Лимонную кислоту или лимонный сок не добавлять - они могут "свернуть" молоко! Залить в чашу блендера охлажденное молоко. Взбить все на максимуме скорости около 2-3 минут, используя импульсный режим, чтобы получилась густая пена. Перелить ягодный коктейль в специальные стаканы для коктейлей, украсить трубочками и подать к столу. Если вы готовите напиток для взрослых, то в бокалы можно добавить несколько кубиков льда, листочки свежей мяты. Рецепт "Ягодный коктейль" готов, приятного аппетита ;)'),
(3,'Шот Облака', 0, '', 3, NULL,
'В охлаждённые стопки налить по 20 мл хорошей водки. Вторым слоем "уложить" серебряную текилу - по 20 мл в каждый шот. По каплям добавить ликёр Блю Курасао. Положить в каждую стопку по 2-3 кубика льда и ложкой уложить слой абсента. Зелёная настойка опуститься вниз, имитируя траву, голубой ликер поднимется и будет "просвечивать" белый лёд как облака. Рецепт "Шот Облака" готов, приятного аппетита ;)'),
(4,'Коктейль Беллини', 0, '', 4, 'Это известный итальянский коктейль на основе игристого вина. Его впервые приготовил Джузеппе Чиприани, который также изобрел карпаччо. В настоящее время коктейль очень популярен в Италии. Его готовят как с персиковым соком, так и с персиковым пюре.',
'Подготовить бокалы для шампанского (флюте). Влить персиковый сок в каждый бокал. Затем влить хорошо охлажденное шампанское (другое белое игристое вино). Готовый коктейль подавать сразу же. По желанию оформить долькой свежего персика. Рецепт "Коктейль Беллини (Bellini)" готов, приятного аппетита ;)'),
(5,'Мохито', 1, '', 1, 'Освежающий и тонизирующий коктейль, безусловный хит в летнее время.',
'Разрезаем половину лайма на четыре части. Одну дольку оставляем для оформления. Кладем лайм в стакан. Всыпаем сахар. Мнем ингредиенты в стакане с помощью мадлера. Обрываем листья мяты и бросаем в стакан. Мнем все вместе еще раз. Теперь нам нужно добавить ледяную крошку. Чтобы ее сделать, необходимо кубики льда положить в ткань. И растолочь.Всыпаем крошку льда в стакан, перемешиваем барной ложкой. Вливаем ром. А затем спрайт. Оформляем коктейль оставшейся долькой лайма, веточкой мяты и ставим трубочки.Рецепт "Мохито (Mojito)" готов, приятного аппетита. Вопросы, предложения и пожелания - пишите в комментариях, я с радостью всем отвечу.'),
(6,'Пина Колада', 1, '', 2, 'Визитная карточка Пуэрто-Рико. Такое вы можете приготовить легко и быстро в домашних условиях. Пина Колада - котейль на века, а если без рома, то и для всех. Очень густой и невероятно душистый! Даже безакогольный вариант отдает вкусом знаменитых Карибов. Ананас и кокосовое молоко вершат свое экзотическое дело.', 
'Из банки консервированных ананасов слить сок. Ананасовые кольца произвольно порезать. Сложить порезанный ананас в блендер,  добавить слитый сок. Влить сироп с ароматом "Пина Колада". Добавить кокосовое молоко или сливки. Взбить коктейль до однородности на большой скорости. Разлить по бокалам, украсить цветной кокосовой стружкой. Подавать охлажденным.'),
(7,'Лунная ночь', 1, '', 2, 'Для прекрасного романтического вечера!', 
' Положить в блендер замороженную клубнику. Залить молоком и добавить мороженое. Смешать ингредиенты при высоком режиме в течение 2-3 мин. Разлить по стаканам. Приятного аппетита:)');

INSERT INTO `barman`.`comment` (`child_id`, `parent_id`, `cocktail_id`, `user_id`, `date`, `text`) 
VALUES 
(1, NULL, 2, 1, NOW(),'Приятный летний коктейль. Спасибо!'),
(2, 1, 2, 2, NOW(),'А мне не очень понравился. Как по моему, он слишком сладкий.'),
(3, 1, 2, 3, NOW(),'Согласен! Мой брат был в восторге!'),
(4, 2, 2, 1, NOW(),'На вкус и цвет..:)'),
(5, NULL, 3, 4, NOW(),'Довольно интересный вкус.'),
(6, 5, 3, 5, NOW(),'Отается приятное послевкусие. Что это?');


INSERT INTO `barman`.`ingredient` (`id`, `name`)
VALUES
(1, 'банан'),
(2, 'молоко'),
(3, 'мороженое'),
(4, 'черешня'),
(5, 'ежевика'),
(6, 'клубника'),
(7, 'сахар'),
(8, 'водка'),
(9, 'текила'),
(10, 'абсент'),
(11, 'ликер брю кюрасао'),
(12, 'сок персиковый'),
(13, 'шампанское'),
(14, 'ром белый'),
(15, 'мята свежая'),
(16, 'лайм'),
(17, 'сахар тросниковый'),
(18, 'лед дробленый'),
(19, 'спрайт'),
(20, 'ананасы консервированные'),
(21, 'кокосовое молоко'),
(22, 'сироп'),
(23, 'кокосовая стружка');

INSERT INTO `barman`.`ingredient_cocktail` (`cocktail_id`,`ingredient_id`,`count`,`dimension_id`)
VALUES
(1, 2, 0.34, 8),
(1, 3, 67, 2),
(2, 4, 25, 2),
(2, 5, 25, 2),
(2, 6, 25, 2),
(2, 7, 12.5, 2),
(2, 2, 100, 7),
(3, 8, 20, 7),
(3, 9, 20, 7),
(3, 10, 10, 7),
(3, 11, 5, 7),
(4, 12, 25, 7),
(4, 13, 50, 7),
(5, 14, 60, 7),
(5, 15, 3, 5),
(5, 16, 0.5, 5),
(5, 17, 1, 3),
(5, 18, 0.75, 8),
(5, 19, 100, 7),
(6, 20, 0.25, 9),
(6, 21, 125, 7),
(6, 22, 50, 7),
(6, 23, 10, 2),
(7, 6, 150, 2),
(7, 3, 240, 2),
(7, 2, 1.5, 7);

INSERT INTO `barman`.`mark_cocktail` (`id`,`mark`,`cocktail_id`,`user_id`)
VALUES
(1, 8, 1, 2),
(2, 7, 1, 3),
(3, 10, 2, 3),
(4, 5, 2, 1),
(5, 9, 3, 4),
(6, 8, 3, 5),
(7, 7, 4, 1),
(8, 5, 4, 2);

INSERT INTO `barman`.`mark_user` (`id`,`mark`,`user_marked_id`,`user_mark_id`)
VALUES
(1, 7, 3, 1),
(2, 9, 3, 2),
(3, 10, 4, 1),
(4, 9, 4, 2),
(5, 8, 5, 1),
(6, 5, 5, 2);

