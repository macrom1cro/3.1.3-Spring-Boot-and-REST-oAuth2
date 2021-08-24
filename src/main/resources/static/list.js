const table = document.querySelector('.table');

const url = 'http://localhost:8080/admin/users';

fetch(url)
    .then(res => console.log(res));