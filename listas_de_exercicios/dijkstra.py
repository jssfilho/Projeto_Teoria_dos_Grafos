from grafo import *
from math import inf


def criar_matriz(g):
    lista_arestas=[]
    for j in g.get_aresta().values():
        lista_arestas.append(j)
    lista_vertices = g.get_vertice()
    matriz_a = []
    for i in range(len(lista_vertices)):
        matriz_a.append([])
        for j in range(len(lista_vertices)):
            if (g.existeAresta(lista_vertices[i] + '-' +lista_vertices[j])):
                matriz_a[i].append(lista_arestas.count(lista_vertices[i] + '-' +lista_vertices[j]))
            else:
                matriz_a[i].append(0)
    return matriz_a

def atribuir_pesos(matriz):
    for i in range(len(matriz)):
        for j in range(len(matriz)):
            if (matriz[i][j]>0):
                print("Digite um peso para a aresta: ", g.N[i]+'-'+g.N[j])
                p=int(input())
                matriz[i][j]=p
    return matriz

def menor_vertice(g, fi, beta):

    vertices=g.N
    minimo = 0

    for v in range(len(vertices)):
        if (fi[v]==0 and beta[v]<inf):
            minimo = v

    return minimo

def algDijkstra(matriz, v_inicial, v_final):
    beta = []
    phi={}
    fi = []
    for i in g.N:
        beta.append(inf)
        fi.append(0)

    beta[g.N.index(v_inicial)]=0
    v = g.N.index(v_inicial)
    for i in range(len(matriz)):
        v = menor_vertice(g, fi, beta)
        fi[v]=1
        for j in range(len(matriz)):
            if(fi[j]==0 and matriz[v][j]>0 and beta[j]>beta[v]+matriz[v][j]):
                beta[j] = beta[v] + matriz[v][j]
                phi[j] = v

    if phi[g.N.index(v_final)] != None:
        caminho=list()
        a = g.N.index(v_final)
        while a != None:
            caminho.insert(0, a)
            a = phi[a]
            if a==g.N.index(v_inicial):
                caminho.insert(0, a)
                break
        for y in caminho:
            if y != g.N.index(v_final):
                print(g.N[y], end="->")
            else:
                print(g.N[y])
    else:
        print("Não existe um caminho entre os vertices!")



g=Grafo()
g.adicionaVertice('A')
g.adicionaVertice('B')
g.adicionaVertice('C')
g.adicionaVertice('D')
g.adicionaVertice('E')
g.adicionaVertice('F')
#g.adicionaVertice('T')
#g.adicionaVertice('Z')
#g.adicionaVertice('B')
#g.adicionaVertice('Y')
g.adicionaAresta('a1','A-B')
g.adicionaAresta('a2','B-C')
g.adicionaAresta('a3','B-D')
g.adicionaAresta('a4','B-E')
g.adicionaAresta('a5','D-E')
g.adicionaAresta('a6','C-F')
g.adicionaAresta('a7','E-F')
#g.adicionaAresta('a8','M-T')
#g.adicionaAresta('a9','Z-Z')

m_triz=atribuir_pesos(criar_matriz(g))
algDijkstra(m_triz, 'A', 'F')