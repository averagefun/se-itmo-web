// clock
const DELAY = 13000;

function getDate(date) {
    let day = date.getDate();
    let month = date.getMonth() + 1;
    let year = date.getFullYear();

    day = (day < 10) ? '0' + day : day;
    month = (month < 10) ? '0' + month : month;

    return `${day}.${month}.${year}`;
}

function getTime(date) {
    let hours = date.getHours();
    let minutes = date.getMinutes();
    let seconds = date.getSeconds();

    hours = (hours < 10) ? '0' + hours : hours;
    minutes = (minutes < 10) ? '0' + minutes : minutes;
    seconds = (seconds < 10) ? '0' + seconds : seconds;

    return `${hours}:${minutes}:${seconds}`;
}

function setCurrentDateTime() {
    const date = new Date();
    const clock = document.querySelector('.main__datetime #my-clock');
    clock.innerHTML = getDate(date) + ' ' + getTime(date)
}

setCurrentDateTime();
setInterval(setCurrentDateTime, DELAY);