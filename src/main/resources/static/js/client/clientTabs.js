function openTab(evt, tab) {
    var i, tabcontent, tablinks;

    // получаем все дивы вкладок и скрываем их, устанавливая display = none
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // делаем все кнопки не активными
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // показываем текущую вкладку и делаем кнопку текущей вкладки активной
    document.getElementById(tab).style.display = "block";
    evt.currentTarget.className += " active";
}