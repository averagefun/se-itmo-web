<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <link rel="icon" href="img/two.png">
    <title>Lab2 #2381</title>
</head>

<body>
    <div class="wrapper">
        <header class="header">
            <div class="container">
                <div class="header__row">
                    <div class="header__title">
                        <a href="">Andrew B, P32081</a>
                    </div>
                    <nav class="header__menu menu">
                        <div class="menu__icon">
                            <span></span>
                        </div>
                        <div class="menu__body">
                            <ul class="menu__list">
                                <li class="menu__item"><a href="">Lab1</a></li>
                                <li class="menu__item"><a href="">Lab2</a></li>
                                <li class="menu__item"><a href="">Lab3</a></li>
                                <li class="menu__item"><a href="">Lab4</a></li>
                                <li class="menu__item"><a
                                        href="https://github.com/averagefun/ITMO-WEB-LABS.git">GitHub</a></li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
        </header>

        <main class="main">
            <div class="container">
                <h1 class="main__title">Лабораторная работа #2, вариант 2381</h1>
                <div class="main__row">
                    <div class="main__left-block">
                        <canvas id="graph"></canvas>
                        <form class="form" method="get">
                            <label class="form__label">X</label>
                            <div class="form__row">
                                <button class="form__x-btn" name="r" type="button" value="-5">-5</button>
                                <button class="form__x-btn" name="r" type="button" value="-4">-4</button>
                                <button class="form__x-btn" name="r" type="button" value="-3">-3</button>
                                <button class="form__x-btn" name="r" type="button" value="-2">-2</button>
                                <button class="form__x-btn" name="r" type="button" value="-1">-1</button>
                                <button class="form__x-btn" name="r" type="button" value="0">0</button>
                                <button class="form__x-btn" name="r" type="button" value="1">1</button>
                                <button class="form__x-btn" name="r" type="button" value="2">2</button>
                                <button class="form__x-btn" name="r" type="button" value="3">3</button>
                            </div>

                            <label for="Y" class="form__label">Y</label>
                            <div class="form__row">
                                <input id="Y" required class="form__number-input" name="y" placeholder="Введите значение [-5 ... 3]"
                                       type="number" step="any" min="-5" max="3" value="">
                            </div>

                            <label for="R" class="form__label">R</label>
                            <div class="form__row">
                                <input id="R" required class="form__number-input" name="y" placeholder="Введите значение [1 ... 4]"
                                       type="number" min="1" max="4" value="">
                            </div>

                            <div class="form__row">
                                <button class="form__big-btn" disabled type="submit">Отправить</button>
                                <button class="form__big-btn" type="reset">Очистить</button>
                            </div>

                        </form>
                    </div>
                    <div class="main__table-block">
                        <table class="main__table">
                            <thead>
                            <tr>
                                <th>X</th>
                                <th>Y</th>
                                <th>R</th>
                                <th>Запуск</th>
                                <th>Работа</th>
                                <th>Результат</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </main>

        <footer class="footer">
            <div class="container">
                <div class="footer__text">
                    <a class="footer__title" href="#">
                        &copy 2022 / ИТМО ВТ
                    </a>
                </div>
            </div>
        </footer>
    </div>

    <script type='text/javascript' src="js/script.js"></script>
    <script type='text/javascript' src="js/graph.js"></script>

</body>

</html>