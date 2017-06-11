-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Июн 11 2017 г., 12:35
-- Версия сервера: 10.1.21-MariaDB
-- Версия PHP: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `armasecond`
--

-- --------------------------------------------------------

--
-- Структура таблицы `d_antonym`
--

CREATE TABLE `d_antonym` (
  `ID` varchar(222) COLLATE utf8_unicode_ci NOT NULL,
  `VALUE` varchar(222) COLLATE utf8_unicode_ci NOT NULL,
  `SECVALUE` varchar(222) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Дамп данных таблицы `d_antonym`
--

INSERT INTO `d_antonym` (`ID`, `VALUE`, `SECVALUE`) VALUES
('2e727568-b836-49d3-b47d-bebf514103fc', 'қорқақ', 'өжет');

-- --------------------------------------------------------

--
-- Структура таблицы `d_synonym`
--

CREATE TABLE `d_synonym` (
  `ID` varchar(222) COLLATE utf8_unicode_ci NOT NULL,
  `VALUE` varchar(222) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Дамп данных таблицы `d_synonym`
--

INSERT INTO `d_synonym` (`ID`, `VALUE`) VALUES
('36645459-c094-402d-a4e5-3e456a0d40b6', 'асырайды бағады'),
('44be4f4e-1dce-497d-afc3-8f966eabe402', 'азығы тамағықорегі'),
('4e0119e0-2aee-49da-9bf4-f40d87e23526', 'am есім'),
('73338fdc-a727-4cf6-a847-52a621977114', 'жақын таяу жуық'),
('73faa85a-4a30-4146-89e5-b3f196ebdc8c', 'тұнық таза мөлдір'),
('7c33341d-3006-4eef-a957-ac70481180b0', 'жоламайды жақындамайды'),
('a3435355-d2e0-425a-985c-25784d968181', 'оты өрті'),
('a4ee6b36-2116-4388-8a47-8c15ac9ad407', 'көрікті келбетті ажарлы өңді көркем әдемі сұлу'),
('e69e2e5b-01ce-44dd-a449-623d065f29eb', 'Батыр Батыя кахарман ержурек'),
('f935c3bc-b16a-48d8-8009-2372d655c521', 'ораза рамазан ');

-- --------------------------------------------------------

--
-- Структура таблицы `p_person`
--

CREATE TABLE `p_person` (
  `id` int(11) NOT NULL,
  `value` varchar(222) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `d_antonym`
--
ALTER TABLE `d_antonym`
  ADD PRIMARY KEY (`ID`);

--
-- Индексы таблицы `d_synonym`
--
ALTER TABLE `d_synonym`
  ADD PRIMARY KEY (`ID`);

--
-- Индексы таблицы `p_person`
--
ALTER TABLE `p_person`
  ADD PRIMARY KEY (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
