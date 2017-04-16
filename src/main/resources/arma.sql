-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Апр 16 2017 г., 15:26
-- Версия сервера: 10.1.21-MariaDB
-- Версия PHP: 7.1.1

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
-- Структура таблицы `all_ending`
--

CREATE TABLE `all_ending` (
  `id` int(11) NOT NULL,
  `value` varchar(222) COLLATE utf8_unicode_ci NOT NULL,
  `d_ending_id` int(11) NOT NULL,
  `d_case_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Дамп данных таблицы `all_ending`
--

INSERT INTO `all_ending` (`id`, `value`, `d_ending_id`, `d_case_id`) VALUES
(1, 'дар', 2, NULL),
(2, 'дер', 2, NULL),
(3, 'тар', 2, NULL),
(4, 'тер', 2, NULL),
(5, 'лар', 2, NULL),
(6, 'лер', 2, NULL),
(7, 'ым', 3, NULL),
(8, 'ім', 3, NULL),
(9, 'м', 3, NULL),
(10, 'ың', 3, NULL),
(11, 'ің', 3, NULL),
(12, 'ң', 3, NULL),
(13, 'ыңыз', 3, NULL),
(14, 'ңыз', 3, NULL),
(15, 'ңіз', 3, NULL),
(16, 'сы', 3, NULL),
(17, 'сі', 3, NULL),
(18, 'ы', 3, NULL),
(19, 'і', 3, NULL),
(20, 'ымыз', 3, NULL),
(21, 'іміз', 3, NULL),
(22, 'мыз', 3, NULL),
(23, 'міз', 3, NULL),
(24, 'ың', 3, NULL),
(25, 'ің', 3, NULL),
(26, 'ң', 3, NULL),
(27, 'ыңыз', 3, NULL),
(28, 'іңіз', 3, NULL),
(29, 'ңыз', 3, NULL),
(30, 'ңіз', 3, NULL),
(31, 'сы', 3, NULL),
(32, 'сі', 3, NULL),
(33, 'ы', 3, NULL),
(34, 'і', 3, NULL),
(35, 'мын', 4, NULL),
(36, 'мін', 4, NULL),
(37, 'мыз', 4, NULL),
(38, 'бын', 4, NULL),
(39, 'бін', 4, NULL),
(40, 'быз', 4, NULL),
(41, 'біз', 4, NULL),
(42, 'пын', 4, NULL),
(43, 'пін', 4, NULL),
(44, 'пыз', 4, NULL),
(45, 'піз', 4, NULL),
(46, 'сың', 4, NULL),
(47, 'сыз', 4, NULL),
(48, 'сің', 4, NULL),
(49, 'сіз', 4, NULL),
(50, 'сыңдар', 4, NULL),
(51, 'сыздар', 4, NULL),
(52, 'сіңдер', 4, NULL),
(53, 'сіздер', 4, NULL),
(54, 'ның', 1, NULL),
(55, 'нің', 2, NULL),
(56, 'дың', 3, NULL),
(57, 'дің', 4, NULL),
(58, 'тың', 3, NULL),
(59, 'тің', 4, NULL),
(60, 'ға', 1, NULL),
(61, 'ге', 2, NULL),
(62, 'қа', 3, NULL),
(63, 'ке', 4, NULL),
(64, 'ны', 1, NULL),
(65, 'ні', 2, NULL),
(66, 'ды', 3, NULL),
(67, 'ді', 4, NULL),
(68, 'ты', 3, NULL),
(69, 'ті', 4, NULL),
(70, 'да', 1, NULL),
(71, 'де', 2, NULL),
(72, 'та', 3, NULL),
(73, 'те', 4, NULL),
(74, 'нан', 1, NULL),
(75, 'нен', 2, NULL),
(76, 'тан', 3, NULL),
(77, 'тен', 4, NULL),
(78, 'дан', 3, NULL),
(79, 'ден', 4, NULL),
(80, 'мен', 1, NULL),
(81, 'бен', 2, NULL),
(82, 'пен', 3, NULL);

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
-- Структура таблицы `d_case`
--

CREATE TABLE `d_case` (
  `id` int(11) NOT NULL,
  `name` varchar(222) COLLATE utf8_unicode_ci NOT NULL,
  `qs` varchar(222) COLLATE utf8_unicode_ci NOT NULL,
  `c_disc` varchar(222) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Дамп данных таблицы `d_case`
--

INSERT INTO `d_case` (`id`, `name`, `qs`, `c_disc`) VALUES
(1, 'Атау септік', 'кім? не?', 'Жалғауы жоқ'),
(2, 'Ілік септік', 'Кімнің? Ненің?', 'Дауысты және  м, н, ң, р, л дауыссыздарынан соң\r\n- мен -менен - з,ж дыбыстарынан соң\r\n- бен - бенен к, қ, п, с, т, ф, ш, б, в, г, д дыбыстарынан соң  - пен - пенен '),
(3, 'Барыс септік', 'Кімге? Неге? Қайда?', 'Дауысты және н, ң, м, ж, р, л, й дыбыстары- нан соң) - ға - ге -а –е - п, с, т, ф, ш, б, в, г, д дыбыстарынан соң - қа - ке -1 және 2 жақтың тәуелдік жалғауларынан кейін  -а, - е-\r\n-а, - е-)3 жақта'),
(4, 'Табыс септік', 'Кімді? Нені? Кімін? Несін?', '-ны - ні - н м, н, ң , з, ж, р, л, й, у\r\nдыбыстарынан соң- ды - ді - к, қ,п,с,т, ф, ш, б, в, г, д\r\nдыбыстарынан соң - ты - ті - '),
(5, 'Жатыс септік', 'Кімде? Неде? Қайда? Қашан?', 'Дауысты және  \r\nм, н, ң, з, ж, л, р, й дауыссыздарынан соң - да - - де - -сы, - сі- тәуелдік жалғауларынан соң) - нда - - нде - к, қ, п, с, т, ф, ш, б, в, г, д дыбыстарынан соң - та - - те '),
(6, 'Шығыс септік', 'Кімнен? Қайдан? Қалай?', 'Дауысты және з, ж, р, л дауыссыздарынан соң - дан - ден - м, н, ң, сы, сі дыбыстарынан соң - нан - нен - к, қ, п, с, т, ф, ш, б, в\r\nдыбыстарынан соң  - тан - тен - '),
(7, 'Көмектес септік', 'Кіммен? Немен? Қалай?', 'Дауысты және  м, н, ң, р, л дауыссыздарынан соң - мен -менен - з,ж дыбыстарынан соң\r\n- бен - бенен к, қ, п, с, т, ф, ш, б, в, г, д дыбыстарынан соң   - пен - пенен ');

-- --------------------------------------------------------

--
-- Структура таблицы `d_ending`
--

CREATE TABLE `d_ending` (
  `ID` int(6) NOT NULL,
  `NAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Дамп данных таблицы `d_ending`
--

INSERT INTO `d_ending` (`ID`, `NAME`) VALUES
(1, 'Септік'),
(2, 'Көптік'),
(3, 'Тәуелдiк'),
(4, 'Жіктік');

-- --------------------------------------------------------

--
-- Структура таблицы `d_suffix`
--

CREATE TABLE `d_suffix` (
  `ID` int(11) NOT NULL,
  `TYPE` varchar(222) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Дамп данных таблицы `d_suffix`
--

INSERT INTO `d_suffix` (`ID`, `TYPE`) VALUES
(1, 'Сөз тудырушы'),
(2, 'Сөз түрлендіруші');

-- --------------------------------------------------------

--
-- Структура таблицы `game_result`
--

CREATE TABLE `game_result` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `game_Id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `uName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `result` int(11) NOT NULL,
  `info` varchar(5000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `d_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Дамп данных таблицы `game_result`
--

INSERT INTO `game_result` (`id`, `game_Id`, `uName`, `result`, `info`, `d_date`) VALUES
('0c86b855-9a07-469e-a67e-32fb5e6ab90f', 'fillWords', 'abzal', 0, '{\"data\":[{\"center\":\"_\",\"right\":\"та\",\"word\":\"ата\",\"id\":\"f3c8c6eb-6164-476f-8933-6cd67dcf0762\",\"result\":false},{\"center\":\"_\",\"right\":\"үз\",\"word\":\"күз\",\"id\":\"495bddd5-764f-4b29-9836-ad11f8d28d39\",\"result\":false},{\"left\":\"балап\",\"center\":\"_\",\"right\":\"н\",\"word\":\"балапан\",\"id\":\"a633fa76-e8f7-469f-afa8-efaddb3ae21d\",\"result\":false},{\"left\":\"көк\",\"center\":\"_\",\"right\":\"ем\",\"word\":\"көктем\",\"id\":\"2d5a8461-5565-49b5-88e8-43bfd1cdfa1e\",\"result\":false},{\"left\":\"қ\",\"center\":\"_\",\"right\":\"зақстан\",\"word\":\"қазақстан\",\"id\":\"74f013a5-f0e0-4637-9617-934452848a22\",\"result\":false},{\"center\":\"_\",\"right\":\"аз\",\"word\":\"жаз\",\"id\":\"15210d64-9a72-4d6f-9ec1-8c78ad845059\",\"result\":false},{\"center\":\"_\",\"right\":\"өрт\",\"word\":\"төрт\",\"id\":\"qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq\",\"result\":false},{\"left\":\"сәлеме\",\"center\":\"_\",\"right\":\"сізбе\",\"word\":\"сәлеметсізбе\",\"id\":\"05f8ba0e-c94f-42b4-80e3-b30cda8b4123\",\"result\":false},{\"left\":\"се\",\"center\":\"_\",\"right\":\"із\",\"word\":\"сегіз\",\"id\":\"qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq1\",\"result\":false}],\"total\":0}', '2017-04-16 00:00:00'),
('4b1f9a4f-ef05-4b47-8399-ab6b50843ede', 'fillWords', 'abzal', 100, '{\"data\":[{\"left\":\"се\",\"center\":\"г\",\"right\":\"із\",\"word\":\"сегіз\",\"id\":\"qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq1\",\"result\":true},{\"left\":\"қ\",\"center\":\"а\",\"right\":\"зақстан\",\"word\":\"қазақстан\",\"id\":\"74f013a5-f0e0-4637-9617-934452848a22\",\"result\":true},{\"center\":\"қ\",\"right\":\"ыс\",\"word\":\"қыс\",\"id\":\"600dfe39-aab7-4ab5-8810-ed33dc84b50c\",\"result\":true},{\"left\":\"м\",\"center\":\"ы\",\"right\":\"сық\",\"word\":\"мысық\",\"id\":\"aa8a092b-97fc-41e8-8fc7-4800bf32d321\",\"result\":true},{\"center\":\"т\",\"right\":\"өрт\",\"word\":\"төрт\",\"id\":\"qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq\",\"result\":true},{\"center\":\"к\",\"right\":\"үз\",\"word\":\"күз\",\"id\":\"495bddd5-764f-4b29-9836-ad11f8d28d39\",\"result\":true},{\"left\":\"сәлемет\",\"center\":\"с\",\"right\":\"ізбе\",\"word\":\"сәлеметсізбе\",\"id\":\"05f8ba0e-c94f-42b4-80e3-b30cda8b4123\",\"result\":true},{\"left\":\"бал\",\"center\":\"а\",\"right\":\"пан\",\"word\":\"балапан\",\"id\":\"a633fa76-e8f7-469f-afa8-efaddb3ae21d\",\"result\":true},{\"left\":\"ә\",\"center\":\"к\",\"right\":\"е\",\"word\":\"әке\",\"id\":\"51e65471-8a3a-44e1-af7f-0bf75b9113c3\",\"result\":true}],\"total\":100}', '2017-04-16 00:00:00');

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
('game_role', 'aknur', 0),
('game_role', 'aknur', 2);

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
('abzal', 'e10adc3949ba59abbe56e057f20f883e', ' '),
('admin', '64c5b12b7729e5076eaa577436042951', 's'),
('aknur', 'e10adc3949ba59abbe56e057f20f883e', '');

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
('abzal', 'Абзал', 'Аманжол', NULL, 'abzal@qq.kz', 0),
('admin', 'asd', 'asd', 'asdasd', 'abzal_amanzhol_94@mail.ru', 0),
('aknur', 'Акнур', 'Оразбаева', '', '', 0);

-- --------------------------------------------------------

--
-- Структура таблицы `video_lessons`
--

CREATE TABLE `video_lessons` (
  `id` int(11) NOT NULL,
  `value` varchar(222) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(22) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Дамп данных таблицы `video_lessons`
--

INSERT INTO `video_lessons` (`id`, `value`, `description`) VALUES
(1, 'https://www.youtube.com/embed/pw8qgxpwoZI?list=PLRnwb2a4Ecp5tizeCb_34Xg35RigC6zBO', 'ТілашарENTER серия 1'),
(2, 'https://www.youtube.com/embed/02Vqc-Ku2q8?list=PLRnwb2a4Ecp5tizeCb_34Xg35RigC6zBO', 'ТiлашарENTER серия 2'),
(3, 'https://www.youtube.com/embed/I9lPIyjIrgU?list=PLRnwb2a4Ecp5tizeCb_34Xg35RigC6zBO', 'ТілашарENTER серия 3'),
(4, 'https://www.youtube.com/embed/1BbwAfGnHp4?list=PLRnwb2a4Ecp5tizeCb_34Xg35RigC6zBO\"', 'ТілашарENTER серия 4'),
(5, 'https://www.youtube.com/embed/cSHrzQ6kNBY?list=PLRnwb2a4Ecp5tizeCb_34Xg35RigC6zBO', 'ТілашарENTER серия 5'),
(6, 'https://www.youtube.com/embed/Y7Zctrc7Oko?list=PLRnwb2a4Ecp5tizeCb_34Xg35RigC6zBO', 'ТілашарENTER серия 6'),
(7, 'https://www.youtube.com/embed/nvsd96WLmNw?list=PLRnwb2a4Ecp5tizeCb_34Xg35RigC6zBO', 'ТілашарENTER серия 7'),
(8, 'https://www.youtube.com/embed/n28i-hbcD7s?list=PLRnwb2a4Ecp5tizeCb_34Xg35RigC6zBO', 'ТілашарENTER серия 8'),
(9, 'https://www.youtube.com/embed/ee9u9_bkpgQ?list=PLRnwb2a4Ecp5tizeCb_34Xg35RigC6zBO', 'ТілашарENTER серия 9'),
(10, 'https://www.youtube.com/embed/jst_eZV0_UM?list=PLRnwb2a4Ecp5tizeCb_34Xg35RigC6zBO', 'ТілашарENTER серия 10'),
(11, 'https://www.youtube.com/embed/SpWngzR_3Ak?list=PLRnwb2a4Ecp5tizeCb_34Xg35RigC6zBO', 'ТілашарENTER серия 11'),
(12, 'https://www.youtube.com/embed/N9v8cS-ODBo?list=PLRnwb2a4Ecp5tizeCb_34Xg35RigC6zBO', 'ТілашарENTER серия 12');

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
-- Индексы таблицы `all_ending`
--
ALTER TABLE `all_ending`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `alph_links`
--
ALTER TABLE `alph_links`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `d_case`
--
ALTER TABLE `d_case`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `d_ending`
--
ALTER TABLE `d_ending`
  ADD PRIMARY KEY (`ID`);

--
-- Индексы таблицы `d_suffix`
--
ALTER TABLE `d_suffix`
  ADD PRIMARY KEY (`ID`);

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
-- Индексы таблицы `video_lessons`
--
ALTER TABLE `video_lessons`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `words`
--
ALTER TABLE `words`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `all_ending`
--
ALTER TABLE `all_ending`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=83;
--
-- AUTO_INCREMENT для таблицы `alph_links`
--
ALTER TABLE `alph_links`
  MODIFY `id` int(222) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;
--
-- AUTO_INCREMENT для таблицы `d_case`
--
ALTER TABLE `d_case`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT для таблицы `d_ending`
--
ALTER TABLE `d_ending`
  MODIFY `ID` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT для таблицы `d_suffix`
--
ALTER TABLE `d_suffix`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
