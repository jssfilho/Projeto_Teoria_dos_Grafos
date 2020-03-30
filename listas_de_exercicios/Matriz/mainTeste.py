from grafo import*
from matriz_g import*
g=Grafo()

#g.adicionaVertice('W')
g.adicionaVertice('J')
g.adicionaVertice('C')
g.adicionaVertice('E')
g.adicionaVertice('P')
g.adicionaVertice('M')
g.adicionaVertice('T')
g.adicionaVertice('Z')
#g.adicionaVertice('B')
#g.adicionaVertice('Y')
g.adicionaAresta('a1','J-C')
g.adicionaAresta('a2','E-C')
g.adicionaAresta('a3','C-E')
g.adicionaAresta('a4','C-P')
g.adicionaAresta('a5','C-C')
g.adicionaAresta('a6','C-M')
g.adicionaAresta('a7','C-T')
g.adicionaAresta('a8','M-T')
g.adicionaAresta('a9','Z-Z')

m=Matriz(g)
m.algWarshall()