function verify() {
    var password = document.forms['form']['password'].value;
    var username = document.forms['form']['username'].value;
    if (password == null || password == "" || username == null || username == "") {
        document.getElementById("error").innerHTML = "User name and password" +
            " are required";
        return false;
    }

    var checkboxes = document.getElementsByName("authorities");
    var okay=false;
    for(var i=0; i<checkboxes.length; i++) {
        if(checkboxes[i].checked) {
            okay=true;
            break;
        }
        document.getElementById("error").innerHTML = "You must select at" +
            " least one role";
    }
    return okay;
}