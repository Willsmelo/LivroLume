/**
 * 
 document.addEventListener("DOMContentLoaded", function() {
    refreshUserList();

    const userForm = document.getElementById("user-form");
    userForm.addEventListener("submit", function(event) {
        event.preventDefault();
        addUser();
    });
});

function refreshUserList() {
    const userList = document.getElementById("user-list");
    userList.innerHTML = "";

    // Fazer uma chamada para a API REST Java para obter a lista de usuários do banco de dados
    // e preencher a lista no HTML.
}

function addUser() {
    const nomeInput = document.getElementById("nome");
    const emailInput = document.getElementById("email");

    const newUser = {
        nome: nomeInput.value,
        email: emailInput.value
    };

    // Fazer uma chamada para a API REST Java para adicionar o novo usuário ao banco de dados.

    nomeInput.value = "";
    emailInput.value = "";

    refreshUserList();
}
 
 
 */