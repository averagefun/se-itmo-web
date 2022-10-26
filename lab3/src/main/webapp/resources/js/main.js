let xValid = false, yValid = false, rValid = false;

const messages = document.getElementById('messages');

// X radio validation
let xInput = document.getElementById('form:X');
const xRadioInput = document.getElementById('xRadio');
const xRadioInputs = xRadioInput.querySelectorAll('input');
xRadioInputs.forEach(radio => {
    radio.addEventListener('input', () => {
        xInput.value = radio.value;
        xValid = true;
        toggleSubmitBtn();
    })
});

// Y input field validation
let yInput = document.getElementById('form:Y');
yInput.addEventListener('input', () => {
    yValid = yInput.value.length > 0;
    toggleSubmitBtn();
})

// R buttons validation
let rInput = document.getElementById('form:R');
const rRadioInput = document.getElementById('rRadio');
const rRadioInputs = rRadioInput.querySelectorAll('input');
rRadioInputs.forEach(radio => {
    radio.addEventListener('input', () => {
        rInput.value = radio.value;
        rValid = true;
        redrawGraph(rInput.value)
        toggleSubmitBtn();
    })
});

const submitBtn = document.getElementById('form:submitBtn');
function toggleSubmitBtn() {
    // check X, Y, R validity
    submitBtn.disabled = !(xValid && yValid && rValid)
}

xInput.value = '';
yInput.value = '';
rInput.value = '';
toggleSubmitBtn()