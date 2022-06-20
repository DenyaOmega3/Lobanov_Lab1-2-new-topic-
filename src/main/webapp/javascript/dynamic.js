window.onload = function saysmth() {
    var today = new Date();
    var dd = today.getDate()+1; //Today I can't create a session
    var mm = today.getMonth()+1; //January = 0 by default
    var yyyy = today.getFullYear();
    if(dd<10) {
        dd='0'+dd
    }
    if(mm<10) {
        mm='0'+mm
    }

    today = yyyy+'-'+mm+'-'+dd;
    document.getElementById("datefield").setAttribute("min",today);
};