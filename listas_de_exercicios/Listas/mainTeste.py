from grafo import *
from funcoes_q3 import *
#J, C, E, P, M, T, Z
#a1(J-C), a2(C-E), a3(C-E), a4(C-P), a5(C-P), a6(C-M), a7(C-T), a8(M-T), a9(T-Z)
g=Grafo()
g.adicionaVertice('W')
g.adicionaVertice('J')
g.adicionaVertice('C')
#g.adicionaVertice('E')
g.adicionaVertice('P')
#g.adicionaVertice('M')
#g.adicionaVertice('T')
#g.adicionaVertice('Z')
#g.adicionaVertice('H')
g.adicionaVertice('Y')
g.adicionaAresta('a1','J-C')
g.adicionaAresta('a2','C-W')
g.adicionaAresta('a3','J-W')
g.adicionaAresta('a4','J-J')
g.adicionaAresta('a5','C-C')
g.adicionaAresta('a6','W-W')
g.adicionaAresta('a7','P-Y')
#g.adicionaAresta('a8','Y-H')
#g.adicionaAresta('a9','H-W')
#g.adicionaAresta('a10','T-P')
u=Funcoes(g)

#o print abaixo serve para testes
print(u.himself())
