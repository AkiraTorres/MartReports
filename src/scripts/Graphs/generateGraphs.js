const allData = [
    {
        "Local A": 10,
        "Local B": 20,
        "Local C": 2,
        "Local D": 50
    },
    {
        "15/06/2023": 10,
        "13/07/2023": 2,
        "08/08/2023": 50,
        "22/09/2023": 15,
        "30/10/2023": 5
    }
]

// set the dimensions and margins of the graph
const width = 350,
    height = 350,
    margin = 40;

// The radius of the pieplot is half the width or half the height (smallest one). I subtract a bit of margin.
const radius = Math.min(width, height) / 2 - margin

// append the svg object to the div called 'my_dataviz'
const svg = d3.select("#grafico01")
    .append("svg")
    .attr("width", width)
    .attr("height", height)
    .append("g")
    .attr("transform", `translate(${width / 2}, ${height / 2})`);

// Create dummy data
const data = allData[0]

// set the color scale
const color = d3.scaleOrdinal()
    .range(d3.schemeSet2);

// Compute the position of each group on the pie:
const pie = d3.pie()
    .value(function (d) { return d[1] })
const data_ready = pie(Object.entries(data))
// Now I know that group A goes from 0 degrees to x degrees and so on.

// shape helper to build arcs:
const arcGenerator = d3.arc()
    .innerRadius(0)
    .outerRadius(radius)

// Build the pie chart: Basically, each part of the pie is a path that we build using the arc function.
svg
    .selectAll('mySlices')
    .data(data_ready)
    .join('path')
    .attr('d', arcGenerator)
    .attr('fill', function (d) { return (color(d.data[0])) })
    .attr("stroke", "black")
    .style("stroke-width", "2px")
    .style("opacity", 0.7)

// Now add the annotation. Use the centroid method to get the best coordinates
svg
    .selectAll('mySlices')
    .data(data_ready)
    .join('text')
    .text(function (d) { return "" + d.data[0] })
    .attr("transform", function (d) { return `translate(${arcGenerator.centroid(d)})` })
    .style("text-anchor", "middle")
    .style("font-size", 17)


// Dados para o gráfico de linhas
const dataLineChart = allData[1];

// Dimensões e margens para o gráfico de linhas
const widthLineChart = 350;
const heightLineChart = 350;
const marginLineChart = { top: 20, right: 30, bottom: 30, left: 40 };

// Calculando as dimensões internas do gráfico de linhas
const innerWidthLineChart = widthLineChart - marginLineChart.left - marginLineChart.right;
const innerHeightLineChart = heightLineChart - marginLineChart.top - marginLineChart.bottom;

// Criando o elemento SVG para o gráfico de linhas
const svgLineChart = d3.select("#grafico02")
    .append("svg")
    .attr("width", widthLineChart)
    .attr("height", heightLineChart)
    .append("g")
    .attr("transform", `translate(${marginLineChart.left},${marginLineChart.top})`);

// Analisando as datas e valores para o gráfico de linhas
const parseDate = d3.timeParse("%d/%m/%Y");
const dates = Object.keys(dataLineChart).map(date => parseDate(date));
const values = Object.values(dataLineChart);

// Escala x para as datas
const xScale = d3.scaleTime()
    .domain([d3.min(dates), d3.max(dates)])
    .range([0, innerWidthLineChart]);

// Escala y para os valores
const yScale = d3.scaleLinear()
    .domain([0, d3.max(values)])
    .range([innerHeightLineChart, 0]);

// Criação das linhas do gráfico
const line = d3.line()
    .x((d, i) => xScale(dates[i]))
    .y(d => yScale(d))
    .curve(d3.curveLinear);

// Adicionando as linhas ao gráfico
svgLineChart.append("path")
    .datum(values)
    .attr("fill", "none")
    .attr("stroke", "steelblue")
    .attr("stroke-width", 2)
    .attr("d", line);

// Adicionando os eixos
svgLineChart.append("g")
    .attr("class", "x-axis")
    .attr("transform", `translate(0,${innerHeightLineChart})`)
    .call(d3.axisBottom(xScale));

svgLineChart.append("g")
    .attr("class", "y-axis")
    .call(d3.axisLeft(yScale));

// Adicionando rótulos aos eixos
svgLineChart.append("text")
    .attr("transform", `translate(${innerWidthLineChart / 2},${innerHeightLineChart + marginLineChart.top + 10})`)
    .style("text-anchor", "middle")
    .text("Data");

svgLineChart.append("text")
    .attr("transform", "rotate(-90)")
    .attr("y", 0 - marginLineChart.left)
    .attr("x", 0 - (innerHeightLineChart / 2))
    .attr("dy", "1em")
    .style("text-anchor", "middle")