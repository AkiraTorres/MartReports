document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");
    form.addEventListener("submit", function (event) {
        event.preventDefault();

        // Obtenha os valores digitados nos campos CPF e senha
        const idInput = document.getElementById("id");
        const senhaInput = document.getElementById("senha");
        const id = idInput.value;
        const senha = senhaInput.value;

        const userData = {
            id: id,
            senha: senha,
        };

        const userDataJSON = JSON.stringify(userData);
        console.log(userDataJSON)

        // Verifique se as credenciais estão corretas
        if (id === "123" && senha === "123") {
            const type = 1; // Se for 1 é Manager e se for 2 é Employee
            if(type == 1) {
                window.location.href = "../pages/UsersPages/UserManagerPage.html";
            }else{
                window.location.href = "../pages/UsersPages/UserEmployeePage.html";
            }
        } 

        // Limpe os campos de entrada
        idInput.value = "";
        senhaInput.value = "";
    });
});

function redirecionarParaCadastro() {
    window.location.href = "../pages/CadastrarPage.html";
}