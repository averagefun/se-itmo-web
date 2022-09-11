// Burger menu
const iconMenu = document.querySelector('.menu__icon');
iconMenu.addEventListener('click', () => {
    iconMenu.classList.toggle('_active');
    document.querySelector('.header__title').classList.toggle('_active');
    document.querySelector('.menu__body').classList.toggle('_active');
    document.body.classList.toggle('_lock');
})

let xValid = false, yValid = false, rValid = false;

// X buttons validation
let selectedXBtn;
const xButtons = document.querySelectorAll('.form__x-btn')
const xInput = document.querySelector('input#X')
xButtons.forEach(btn => {
    btn.addEventListener('click', () => {
        if (selectedXBtn !== btn) {
            if (selectedXBtn !== undefined) {
                selectedXBtn.classList.remove('selected-btn');
            }
            btn.classList.toggle('selected-btn');
            selectedXBtn = btn;
            xInput.value = selectedXBtn.value;
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

// R input field validation
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

// Clear all table data
const clearBtn = document.querySelector('.form__big-btn[type="reset"]');
clearBtn.addEventListener("click", e => {
    e.preventDefault();
    const params = {'clear': true}
    window.location.replace("/lab2/process" + formatParams(params));
})
