<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="java.util.Calendar" %>
<%@ page import="data.Result" %>
<%@ page import="data.ResultList" %>


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
                <h1 class="main__title">Лабораторная работа #2, вариант 2381 <span class="main__title-message"><%=
                (request.getAttribute("message") != null) ? "-> " + request.getAttribute("message") : ""%></span></h1>

                <div class="main__row">
                    <div class="main__left-block">
                        <canvas id="graph"></canvas>
                        <form class="form" method="get" action="${pageContext.request.contextPath}/process">
                            <label class="form__label">X</label>
                            <div class="form__row">
                                <input id="X" type="hidden" name="x" value="">
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
                                <input id="Y" required class="form__number-input" name="y"
                                       type="number" step="any" min="-5" max="3"
                                       placeholder="Введите значение [-5 ... 3]"
                                       value="">
                            </div>

                            <label for="R" class="form__label">R</label>
                            <div class="form__row">
                                <input id="R" required class="form__number-input" name="r"
                                       type="number" min="1" max="4"
                                       placeholder="Введите значение [1 ... 4]"
                                       value="">
                            </div>

                            <label for="username" class="form__label">U</label>
                            <div class="form__row">
                                <input id="username" required class="form__number-input" name="username"
                                       type="text"
                                       placeholder="Введите логин ..."
                                       value="<%=
                (request.getParameter("username") != null) ? request.getParameter("username") : ""%>">
                            </div>

                            <label for="password" class="form__label">P</label>
                            <div class="form__row">
                                <input id="password" required class="form__number-input" name="password"
                                       type="password"
                                       placeholder="Введите пароль ..."
                                       value="<%=
                (request.getParameter("password") != null) ? request.getParameter("password") : ""%>">
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
                            <%
                                ResultList resultList;
                                if (session.getAttribute("results") == null) {
                                    resultList = new ResultList();
                                } else {
                                    resultList = (ResultList) session.getAttribute("results");
                                }

                                for (Result result : resultList) {
                            %>
                            <tr>
                                <td><%=result.getX()%></td>
                                <td><%=result.getY()%></td>
                                <td><%=result.getR()%></td>
                                <td><%=result.getCurrTime()%></td>
                                <td><%=result.getExecTime()%> нс</td>
                                <td><%=result.isHitResult() ? "<span class='hit'>Попадание</span>" : "<span class='miss'>Промах</span>"%></td>
                            </tr>
                            <% } %>
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
                        &copy <%= Calendar.getInstance().get(Calendar.YEAR) %> / ИТМО ВТ
                    </a>
                </div>
            </div>
        </footer>
    </div>

    <script type='text/javascript' src="js/script.js"></script>
    <script type='text/javascript' src="js/graph.js"></script>

</body>

</html>