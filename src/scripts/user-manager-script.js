// Função para lidar com o evento de arrastar e soltar
const dropArea = document.getElementById('drop-area');

dropArea.addEventListener('dragover', (e) => {
    e.preventDefault();
    dropArea.style.border = '2px dashed #333';
});

dropArea.addEventListener('dragleave', () => {
    dropArea.style.border = '2px dashed #ccc';
});

dropArea.addEventListener('drop', (e) => {
    e.preventDefault();
    dropArea.style.border = '2px dashed #ccc';

    const files = e.dataTransfer.files;
    handleFiles(files);
});

// Função para lidar com a seleção de arquivo
const fileInput = document.getElementById('file-input');

fileInput.addEventListener('change', () => {
    const files = fileInput.files;
    handleFiles(files);
});

// Função para lidar com os arquivos selecionados ou arrastados
function handleFiles(files) {
    for (const file of files) {
        console.log('Arquivo selecionado: ' + file.name);

        // Verifica se o arquivo é um tipo de texto (por exemplo, .txt, .html, .csv)
        if (file.type.startsWith('text/')) {
            const reader = new FileReader();

            reader.onload = (event) => {
                const fileContent = event.target.result;
                console.log('Conteúdo do arquivo:');
                console.log(fileContent);

                // Aqui você pode realizar a lógica necessária com o conteúdo do arquivo, como processá-lo ou exibi-lo na página.
            };

            reader.readAsText(file);
        } else {
            console.log('Este arquivo não é um tipo de texto. Não é possível ler o conteúdo.');
        }
    }
}

// Evento de clique para os botões
const inserirDadosButton = document.getElementById('inserir-dados');
const gerarAnaliseButton = document.getElementById('gerar-analise');

inserirDadosButton.addEventListener('click', () => {
    console.log("Os dados foram inseridos")
});

gerarAnaliseButton.addEventListener('click', () => {
    window.location.href = "../../pages/GeneralInsights.html";
});