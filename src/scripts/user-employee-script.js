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


let response = null

// Função para lidar com os arquivos selecionados ou arrastados
function handleFiles(files) {
    for (const file of files) {
        console.log('Arquivo selecionado: ' + file.name);

        // Verifica se o arquivo é um tipo de texto (por exemplo, .txt, .html, .csv)
        if (file.type.startsWith('text/')) {
            const reader = new FileReader();

            reader.onload = (event) => {
                const fileContent = event.target.result;
                // Divida as strings em linhas
                const lines = fileContent.split('\n');
                // Separe a primeira linha para obter as chaves
                const keys = lines[0].split(',');
                // Inicialize um array para armazenar os objetos
                const objectsArray = [];
                // Itere pelas linhas restantes
                for (let i = 1; i < lines.length; i++) {
                    const values = lines[i].split(',');
                    // Crie um objeto usando as chaves e valores correspondentes
                    const obj = {};
                    for (let j = 0; j < keys.length; j++) {
                        obj[keys[j]] = values[j];
                    }
                    // Adicione o objeto ao array
                    objectsArray.push(obj);
                }
                response = JSON.stringify(objectsArray);
            };

            reader.readAsText(file);
        } else {
            console.log('Este arquivo não é um tipo de texto. Não é possível ler o conteúdo.');
        }
    }
}

// Evento de clique para os botões
const inserirDadosButton = document.getElementById('inserir-dados');

inserirDadosButton.addEventListener('click', () => {
    console.log("Os dados foram inseridos")
    // O array de objetos está pronto
    console.log('Conteúdo do arquivo:');
    console.log(response);
});