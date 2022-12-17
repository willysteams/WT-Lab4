function changeForm(form) {
    var i, formType;
    formType = document.getElementsByClassName("formType");
    for (i = 0; i < formType.length; i++) {
        formType[i].style.display = "none";
    }
    document.getElementById(form).style.display = "block";
}