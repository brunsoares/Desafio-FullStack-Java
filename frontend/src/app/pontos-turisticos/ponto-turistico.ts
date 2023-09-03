import { Comentario } from "../comentarios/comentario";
import { Pais } from "../paises/paises";

export class PontoTuristico{
    id: string;
    pais: Pais;
    cidade: string;
    nome: string;
    melhorEstacao: string;
    comentarios: Array<Comentario>;
}