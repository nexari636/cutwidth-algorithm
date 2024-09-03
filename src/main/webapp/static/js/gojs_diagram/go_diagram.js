let POSx=0;
let Posy=0;
let POSx_AUX=POSx;
let tamanio_separacion_nodos=200;
let array_Nodos=[]
let array_Conexiones=[]
let myDiagram=null;

function init_diagram()
{
    myDiagram = new go.Diagram(
        'myDiagramDiv', 
        { 'undoManager.isEnabled': true }
    ); 
    
    myDiagram.nodeTemplate = new go.Node('Auto', {locationSpot: go.Spot.Center, width: 70, height: 70 })
    .add(
        new go.Shape('Circle', {strokeWidth: 1, fill: 'white' }) 
          .bind('fill', 'color'),
          new go.TextBlock({ margin: 8})
          .bind("text").bind("location", "loc", go.Point.parse),
    ).bind("location", "loc", go.Point.parse);

    myDiagram.linkTemplate =
    new go.Link({ curve: go.Curve.Bezier,routing: go.Routing.AvoidsNodes, corner: 100})      
      .add(
        new go.Shape({ strokeWidth: 5 })  
    );

}


function crear_nodos(nodox1,corte,bandera_ultimo)
{
  POSx_AUX=POSx;
  array_Nodos.push({key: `${nodox1}`, text: `${nodox1}`, loc: `${POSx} ${Posy}`})
  POSx+=tamanio_separacion_nodos;
  if(!bandera_ultimo)
    agregar_corte(POSx_AUX,POSx,corte);
}

function crear_conexiones(desde,hasta)
{
  array_Conexiones.push({ from: `${desde}`, to: `${hasta}` });
}

function agregar_corte(posXant,postXact,cantNodoCorte)
{
  myDiagram.add(
    new go.Part("Vertical",{position: new go.Point(((posXant+postXact)/2)-25/2, (-150))})
    .add(
      new go.TextBlock({ text: cantNodoCorte,stroke: "#000",font: "bold 30pt serif"}),
      new go.Shape("LineV", { strokeWidth: 5, stroke: "#0769B2", width: 20, height: 8000, margin: 0, fill: null }),
    
    ));
}

function asignar_nodos_diagrama()
{
  myDiagram.model = new go.GraphLinksModel(array_Nodos,array_Conexiones);
  console.log(myDiagram.model.nodeDataArray.loc);
}


function limpiar_diagrama()
{
    POSx=0;
    Posy=0;
    POSx_AUX=POSx;
    tamanio_separacion_nodos=100;
    array_Nodos=[];
    array_Conexiones=[];     
    myDiagram.div = null;
    myDiagram=null;
    init_diagram();
}