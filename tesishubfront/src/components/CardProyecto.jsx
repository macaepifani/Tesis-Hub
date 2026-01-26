export default function CardProyecto({ proyecto }) {
  return (
    <article className="card-proyecto">
      <span className="categoria">{proyecto.categoria}</span>

      <h3>{proyecto.titulo}</h3>
      <p>{proyecto.descripcion}</p>

      <div className="autor">
        <small>Por {proyecto.autor}</small>
      </div>
    </article>
  );
}
