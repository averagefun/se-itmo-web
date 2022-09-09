// Burger menu
const iconMenu = document.querySelector('.menu__icon');
iconMenu.addEventListener('click', () => {
    iconMenu.classList.toggle('_active');
    document.querySelector('.header__title').classList.toggle('_active');
    document.querySelector('.menu__body').classList.toggle('_active');
    document.body.classList.toggle('_lock');
})

let xValid = false, yValid = false, rValid = false;

// X input field validation
let selectedXBtn;
const xBtns = document.querySelectorAll('.form__x-btn')
xBtns.forEach(btn => {
    btn.addEventListener('click', () => {
        if (selectedXBtn !== btn) {
            if (selectedXBtn !== undefined) {
                selectedXBtn.classList.remove('selected-btn');
            }
            btn.classList.toggle('selected-btn');
            selectedXBtn = btn;
        }
        xValid = true;
        toggleSubmitBtn();
    })
})

// Y input field validation
const yInput = document.querySelector('input#Y')
yInput.addEventListener('input', () => {
    const validityState = yInput.validity;
    yValid = false;
    if (validityState.valueMissing) {
        yInput.setCustomValidity('Поле не может быть пустым.');
    } else if (validityState.rangeUnderflow || validityState.rangeOverflow) {
        yInput.setCustomValidity('Значение должно находиться в отрезке [-5 ... 3].');
    } else {
        yValid = true;
        yInput.setCustomValidity('');
    }
    yInput.reportValidity();
    toggleSubmitBtn();
})

// R input
const rInput = document.querySelector('input#R')
rInput.addEventListener('input', () => {
    const validityState = rInput.validity;
    rValid = false;
    if (validityState.valueMissing) {
        rInput.setCustomValidity('Поле не может быть пустым.');
    } else if (validityState.rangeUnderflow || validityState.rangeOverflow) {
        rInput.setCustomValidity('Значение должно находиться в отрезке [1 ... 4].');
    } else if (validityState.stepMismatch) {
        rInput.setCustomValidity('Значение должно быть целым числом.');
    } else {
        rValid = true;
        redrawGraph(rInput.value)
        rInput.setCustomValidity('');
    }
    rInput.reportValidity();
    toggleSubmitBtn();
})

const submitBtn = document.querySelector('.form__big-btn[type="submit"]');
function toggleSubmitBtn() {
    // check X, Y, R validity
    submitBtn.disabled = !(xValid && yValid && rValid)
}

function formatParams(params) {
    return "?" + Object
        .keys(params)
        .map(function (key) {
            return key + "=" + encodeURIComponent(params[key])
        })
        .join("&")
}

const tbody = document.querySelector('.main__table tbody');

// Submit form
const form = document.querySelector('.form');
form.addEventListener('submit', e => {
    e.preventDefault(); // prevent submitting

    let params = {
        'x': selectedXBtn.value,
        'y': yInput.value,
        'r': rInput.value
    }
    const target = 'php/submit.php' + formatParams(params)

    const xhr = new XMLHttpRequest();
    xhr.open('GET', target);

    xhr.onloadend = () => {
        if (xhr.status === 200) {
            tbody.innerHTML = xhr.response;
            const isHit = document.querySelector('tbody tr:last-child td:last-child span').classList.contains('hit')
            printDotOnGraph(selectedXBtn.value, yInput.value, isHit)
        } else console.log("status: ", xhr.status)
    };

    xhr.send();
})

// Clear all table data
const clearBtn = document.querySelector('.form__big-btn[type="reset"]');
clearBtn.addEventListener("click", e => {
    e.preventDefault();

    let xhr = new XMLHttpRequest();
    xhr.onloadend = () => {
        if (xhr.status === 200) {
            tbody.innerHTML = '';
        } else console.log("status: ", xhr.status)
    };
    xhr.open("POST", "php/clear.php");
    xhr.send();
})


// Get previous table data while loading page
window.onload = () => {
    let xhr = new XMLHttpRequest();
    xhr.onloadend = () => {
        if (xhr.status === 200) {
            const tbody = document.querySelector('.main__table tbody');
            tbody.innerHTML = xhr.response;
        } else console.log("status: ", xhr.status)
    };
    xhr.open("GET", "php/init.php");
    xhr.send();
}