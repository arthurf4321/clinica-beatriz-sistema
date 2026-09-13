const form = document.querySelector('.form-inputs');
const emailInput = document.getElementById('email');
const senhaInput = document.getElementById('senha');

form.addEventListener('submit', function(event) {
    const email = emailInput.value.trim();
    const senha = senhaInput.value.trim();

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    let mensagens = document.querySelectorAll('.erro-msg');
    mensagens.forEach(msg => msg.remove());

    let valid = true;

    if (!email) {
        criarErro(emailInput, 'Por favor, digite seu email.');
        valid = false;
    } else if (!emailRegex.test(email)) {
        criarErro(emailInput, 'Formato de email inválido.');
        valid = false;
    }

    if (!senha) {
        criarErro(senhaInput, 'Por favor, digite sua senha.');
        valid = false;
    }

    if (!valid) {
        event.preventDefault();
    }
});

function criarErro(input, mensagem) {
    const erro = document.createElement('div');
    erro.classList.add('erro-msg');
    erro.innerText = mensagem;
    input.insertAdjacentElement('afterend', erro);
}