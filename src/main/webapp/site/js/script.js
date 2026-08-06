async function buscarLocalizacao() {

    const endereco = "Rua das Flores, 123 São Paulo";

    const response = await fetch(
        `https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(endereco)}`
    );

    const data = await response.json();

    if (data.length > 0) {

        const latitude = data[0].lat;
        const longitude = data[0].lon;

        document.getElementById("mapa").src =
            `https://maps.google.com/maps?q=${latitude},${longitude}&z=15&output=embed`;

    } else {
        alert("Endereço não encontrado!");
    }
}

buscarLocalizacao();