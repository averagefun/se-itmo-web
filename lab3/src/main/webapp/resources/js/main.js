let xValid = false, yValid = false, rValid = false;

// X radio validation
const xInput = document.querySelector('input#X');
const xRadioInputs = document.querySelectorAll('.form__x-col input');
xRadioInputs.forEach(radio => {
    radio.addEventListener('input', () => {
        xInput.value = radio.value;
        xValid = true;
        toggleSubmitBtn();
    })
});

// Y input field validation
const yInput = document.querySelector('input#Y')
yInput.addEventListener('input', () => {
    const validityState = yInput.validity;
    yValid = false;
    if (validityState.valueMissing) {
        yInput.setCustomValidity('Поле не может быть пустым.');
    } else if (validityState.rangeUnderflow || validityState.rangeOverflow) {
        yInput.setCustomValidity('Значение должно находиться в отрезке [-5 ... 5].');
    } else {
        yValid = true;
        yInput.setCustomValidity('');
    }
    yInput.reportValidity();
    toggleSubmitBtn();
})

// R buttons validation
const rInput = document.querySelector('input#R');
const rRadioInputs = document.querySelectorAll('.form__r-col input');
rRadioInputs.forEach(radio => {
    radio.addEventListener('input', () => {
        rInput.value = radio.value;
        rValid = true;
        redrawGraph(rInput.value)
        toggleSubmitBtn();
    })
});

const submitBtn = document.querySelector('.big-btn[type="submit"]');
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
const clearBtn = document.querySelector('.big-btn[type="reset"]');
clearBtn.addEventListener("click", e => {
    e.preventDefault();
    const params = {'clear': true}
    window.location.replace("/lab3/process" + formatParams(params));
})
