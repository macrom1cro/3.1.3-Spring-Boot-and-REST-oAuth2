
fetch("http://localhost:8080/admin/users").then(
    res => {
        res.json().then(
            data => {
                console.log(data);
                if (data.length > 0) {
                    var temp = "";
                    data.forEach((user) => {
                        temp += "<tr>"
                        temp += "<td>" + user.id + "</td>"
                        temp += "<td>" + user.firstName + "</td>"
                        temp += "<td>" + user.lastName + "</td>"
                        temp += "<td>" + user.age + "</td>"
                        temp += "<td>" + user.email + "</td>"
                        temp += "<td>" + user.roles.forEach(role => {
                            temp += `<span>${role.role.substring(5)}</span>`;
                        }) + "</td>"
                        temp += "<td>" + `<a href="/admin/users/${user.id}" class="btn btn-primary" id="edit">Edit</a>` + "</td>"
                        temp += "<td>" + `<a href="/admin/users/${user.id}" class="btn btn-danger" id="delete">Delete</a>` + "</td>"
                        temp += "</tr>"
                    })
                    document.getElementById("data").innerHTML = temp;
                }
            }
        )
    })

// fetch("http://localhost:8080/admin/users/1").then(
//     res => {
//         res.json().then(
//             data => {
//                 console.log(data);
//                 if (data.length > 0) {
//                     var temp = "";
//                     data.forEach((user) => {
//                         document.getElementById("idEdit").innerHTML = user.id
//                         document.getElementById("firstNameEdit").innerHTML = user.firstName
//                         document.getElementById("lastNameEdit").innerHTML = user.lastName
//                         document.getElementById("ageEdit").innerHTML = user.age
//                         document.getElementById("emailEdit").innerHTML = user.email
//                         document.getElementById("passwordEdit").innerHTML = user.password
//                         document.getElementById("rolesEdit").innerHTML = user.role
//                     })
//                     document.querySelector('#editModal').show()
//                 }
//             }
//         )
//     })

// alert('hi');
window.onload = function(){
// $(document).ready(function () {
    $('.btn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (user, status) {
            $('#idEdit').val(user.id);
            $('#firstNameEdit').val(user.firstName);
            $('#lastNameEdit').val(user.lastName);
            $('#ageEdit').val(user.age);
            $('#emailEdit').val(user.email);
            $('#passwordEdit').val(user.password);
            $('#rolesEdit').val(user.role);
        });
        $('#editModal').modal();
    });
// });
}

// $(document).ready(function () {
//     $('#delete').on('click', function (event) {
//         event.preventDefault();
//         var href = $(this).attr('href');
//         $.get(href, function (user, status) {
//             $('#idEdit').val(user.id);
//             $('#firstNameEdit').val(user.firstName);
//             $('#lastNameEdit').val(user.lastName);
//             $('#ageEdit').val(user.age);
//             $('#emailEdit').val(user.email);
//             $('#passwordEdit').val(user.password);
//             $('#rolesEdit').val(user.role);
//         });
//         $('#editModal').modal();
//     });
// });

