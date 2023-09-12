document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");
    form.addEventListener("submit", function (event) {
        event.preventDefault();

        // Obtenha os valores digitados nos campos CPF e senha
        const nomeInput = document.getElementById("nome");
        const typeInput = document.getElementById("type");
        const emailInput = document.getElementById("email");
        const senhaInput = document.getElementById("senha");
        const nome = nomeInput.value;
        const type = typeInput.value;
        const email = emailInput.value;
        const senha = senhaInput.value;

        console.log(nome, type, email, senha)

        // Limpe os campos de entrada
        nomeInput.value = "";
        typeInput.value = "";
        emailInput.value = "";
        senhaInput.value = "";

    });
});

function redirecionarParaLogin() {
    window.location.href = "../pages/LoginPage.html";
}