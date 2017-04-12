-- phpMyAdmin SQL Dump
-- version 4.5.0.2
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Апр 12 2017 г., 07:26
-- Версия сервера: 10.0.17-MariaDB
-- Версия PHP: 5.5.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `arma`
--

-- --------------------------------------------------------

--
-- Структура таблицы `alph_links`
--

CREATE TABLE `alph_links` (
  `id` int(222) NOT NULL,
  `link` text COLLATE utf8_unicode_ci NOT NULL,
  `letter` varchar(3) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Дамп данных таблицы `alph_links`
--

INSERT INTO `alph_links` (`id`, `link`, `letter`) VALUES
(1, 'https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/316222556&amp;', 'А а'),
(2, 'https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/316239157&amp;', 'Ә ә'),
(3, '', 'Б б'),
(4, '', 'В в'),
(5, '', 'Г г'),
(6, '', 'Ғ ғ'),
(7, '', 'Д д'),
(8, 'https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/317296308&amp;', 'Е е'),
(9, '', 'Ё ё'),
(10, '', 'Ж ж'),
(11, '', 'З з'),
(12, 'https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/317296422&amp;', 'И и'),
(13, '', 'Й й'),
(14, '', 'К к'),
(15, '', 'Қ қ'),
(16, '', 'Л л'),
(17, '', 'М м'),
(18, '', 'Н н'),
(19, '', 'Ң ң'),
(20, 'https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/317294720&amp;', 'О о'),
(21, 'https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/317295409&amp;', 'Ө ө'),
(22, '', 'П п'),
(23, '', 'Р р'),
(24, '', 'С с'),
(25, '', 'Т т'),
(26, 'https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/317296678&amp;', 'У у'),
(27, 'https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/317295793&amp;', 'Ұ ұ'),
(28, 'https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/317295932&amp;', 'Ү ү'),
(29, '', 'Ф ф'),
(30, '', 'Х х'),
(31, '', 'Һ һ'),
(32, '', 'Ц ц'),
(33, '', 'Ч ч'),
(34, '', 'Ш ш'),
(35, '', 'Щ щ'),
(36, '', 'ъ'),
(37, 'https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/317296084&amp;', 'Ы ы'),
(38, 'https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/317296198&amp;', 'І і'),
(39, '', 'ь'),
(40, '', 'Э э'),
(41, '', 'Ю ю'),
(42, '', 'Я я');

-- --------------------------------------------------------

--
-- Структура таблицы `game_result`
--

CREATE TABLE `game_result` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `game_Id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `uName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `result` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `groupmembers`
--

CREATE TABLE `groupmembers` (
  `G_NAME` varchar(255) NOT NULL,
  `G_MEMBER` varchar(255) NOT NULL,
  `ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `groupmembers`
--

INSERT INTO `groupmembers` (`G_NAME`, `G_MEMBER`, `ID`) VALUES
('admin_role', 'admin', 88),
('game_role', 'urizat', 0),
('game_role', 'urizat', 2);

-- --------------------------------------------------------

--
-- Структура таблицы `groups`
--

CREATE TABLE `groups` (
  `G_NAME` varchar(255) NOT NULL,
  `G_DESCRIPTION` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `groups`
--

INSERT INTO `groups` (`G_NAME`, `G_DESCRIPTION`) VALUES
('admin_role', 'root - право'),
('game_role', 'кухня');

-- --------------------------------------------------------

--
-- Структура таблицы `test_type`
--

CREATE TABLE `test_type` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Дамп данных таблицы `test_type`
--

INSERT INTO `test_type` (`id`, `name`) VALUES
('fillWords', 'Сөздерді толықтыр'),
('other', 'Басқа сынақ'),
('other2', 'Басқа сынақ 2');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `U_NAME` varchar(255) NOT NULL,
  `U_PASSWORD` varchar(255) NOT NULL,
  `U_DESCRIPTION` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`U_NAME`, `U_PASSWORD`, `U_DESCRIPTION`) VALUES
('admin', '64c5b12b7729e5076eaa577436042951', 's'),
('urizat', 'e10adc3949ba59abbe56e057f20f883e', '');

-- --------------------------------------------------------

--
-- Структура таблицы `user_detail`
--

CREATE TABLE `user_detail` (
  `U_NAME` varchar(255) NOT NULL,
  `FIRSTNAME` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `MIDDLENAME` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `LOCKED` int(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user_detail`
--

INSERT INTO `user_detail` (`U_NAME`, `FIRSTNAME`, `LASTNAME`, `MIDDLENAME`, `EMAIL`, `LOCKED`) VALUES
('admin', 'asd', 'asd', 'asdasd', 'abzal_amanzhol_94@mail.ru', 0),
('urizat', 'Уризат', 'Акжол', '', '', 0),
('weblogic', '????asdsa', '???dasd', '', '', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `words`
--

CREATE TABLE `words` (
  `id` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `value_kz` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Дамп данных таблицы `words`
--

INSERT INTO `words` (`id`, `value_kz`) VALUES
('05f8ba0e-c94f-42b4-80e3-b30cda8b4123', 'Сәлеметсізбе'),
('15210d64-9a72-4d6f-9ec1-8c78ad845059', 'Жаз'),
('2d5a8461-5565-49b5-88e8-43bfd1cdfa1e', 'Көктем'),
('495bddd5-764f-4b29-9836-ad11f8d28d39', 'Күз'),
('51e65471-8a3a-44e1-af7f-0bf75b9113c3', 'Әке'),
('600dfe39-aab7-4ab5-8810-ed33dc84b50c', 'Қыс'),
('74f013a5-f0e0-4637-9617-934452848a22', 'Қазақстан'),
('a633fa76-e8f7-469f-afa8-efaddb3ae21d', 'Балапан'),
('aa8a092b-97fc-41e8-8fc7-4800bf32d321', 'Мысық'),
('adasds54sadsdsddddddddddddddddddd', 'Жеті'),
('afe09951-ae5c-40ad-9ecc-44a411146698', 'Қазақ'),
('e9dc5b3a-dea7-42de-9b35-ee1798c96490', 'Әже'),
('f3c8c6eb-6164-476f-8933-6cd67dcf0762', 'Ата'),
('fc266d3a-35ee-47bc-9891-3d54e721fd60', 'Ана'),
('qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq1', 'Сегіз'),
('qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq2', 'Тоғыз'),
('qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq', 'Төрт');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `alph_links`
--
ALTER TABLE `alph_links`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `game_result`
--
ALTER TABLE `game_result`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`G_NAME`);

--
-- Индексы таблицы `test_type`
--
ALTER TABLE `test_type`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`U_NAME`);

--
-- Индексы таблицы `user_detail`
--
ALTER TABLE `user_detail`
  ADD PRIMARY KEY (`U_NAME`);

--
-- Индексы таблицы `words`
--
ALTER TABLE `words`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `alph_links`
--
ALTER TABLE `alph_links`
  MODIFY `id` int(222) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
