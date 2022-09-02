// Burger menu
const iconMenu = document.querySelector('.menu__icon');
iconMenu.addEventListener('click', (event) => {
    iconMenu.classList.toggle('_active');
    document.querySelector('.header__title').classList.toggle('_active');
    document.querySelector('.menu__body').classList.toggle('_active');
    document.body.classList.toggle('_lock');
})

let xValid = false, yValid = false, rValid = false;

// X input field validation
let selectedXRadio;
const xRadioInputs = document.querySelectorAll('.form__x-col input');
xRadioInputs.forEach(radio => {
    radio.addEventListener('input', () => {
        selectedXRadio = radio;
        xValid = true;
        toggleSubmitBtn();
    })
});

// Y input field validation
const yInput = document.querySelector('input[name="y"]')
yInput.addEventListener('input', () => {
    const validityState = yInput.validity;
    yValid = false;
    if (validityState.valueMissing) {
        yInput.setCustomValidity('Поле не может быть пустым.');
    } else if (validityState.rangeUnderflow || validityState.rangeOverflow) {
        yInput.setCustomValidity('Значение должно находиться в отрезке [-3 ... 3].');
    } else {
        yValid = true;
        yInput.setCustomValidity('');
    }
    yInput.reportValidity();
    toggleSubmitBtn();
})

// R input
let selectedRBtn;
const rBtns = document.querySelectorAll('.form__r-btn')
rBtns.forEach(btn => {
    btn.addEventListener('click', () => {
        if (selectedRBtn !== btn) {
            if (selectedRBtn !== undefined) {
                selectedRBtn.classList.remove('selected-btn');
            }
            btn.classList.toggle('selected-btn');
            selectedRBtn = btn;
        }
        rValid = true;
        redrawGraph(selectedRBtn.value)
        toggleSubmitBtn();
    })
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

    params = {
        'x': selectedXRadio.value,
        'y': yInput.value,
        'r': selectedRBtn.value
    }
    const target = 'php/submit.php' + formatParams(params)
    console.log(target);

    const xhr = new XMLHttpRequest();
    xhr.open('GET', target);

    xhr.onloadend = () => {
        if (xhr.status === 200) {
            console.log(xhr.response);
            tbody.innerHTML = xhr.response;
        } else console.log("status: ", xhr.status)
    };

    xhr.send();
})

// Clear all table data
const clearBtn = document.querySelector('.form__big-btn[type="clear"]');
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