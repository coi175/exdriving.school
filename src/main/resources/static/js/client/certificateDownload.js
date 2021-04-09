function downloadCertificate(a) {
        let data = $('.cert_data', a).text();
        values = data.split('/');

        let PTSans = getPtSans();
        var doc = new jsPDF("landscape");
        doc.addFileToVFS("/PTSans.ttf", PTSans);
        doc.addFont("/PTSans.ttf", "PTSans", "normal");
        doc.setFont("PTSans"); // set font
        let img = new Image;
        img.src = "/sertificat.png";
        doc.addImage(img, 'PNG', 8, 8);
        doc.setFontSize(25);
        doc.text("Номер: " + values[0], 120, 90);
        doc.setFontSize(25);
        doc.text(values[1], 110, 110);
        doc.setFontSize(20);
        doc.text("Оценка: " + values[2], 130, 125);
        doc.setFontSize(15);
        doc.text("Дата: " + values[3], 200, 140);
        doc.save(values[0] + "certificat.png");
}
