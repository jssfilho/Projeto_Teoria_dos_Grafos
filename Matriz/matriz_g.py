from grafo import *

class Matriz:
    lista_vertices = None
    lista_arestas = []
    matriz = None
    def __init__(self, G: object):
        self.G=G
        for j in self.G.get_aresta().values():
            self.lista_arestas.append(j)
        self.lista_vertices=self.G.get_vertice()
        matriz_a = []
        for i in range(len(self.lista_vertices)):
            matriz_a.append([])
            for j in range(len(self.lista_vertices)):
                if (self.G.existeAresta(self.lista_vertices[i]+'-'+self.lista_vertices[j])):
                    matriz_a[i].append(self.lista_arestas.count(self.lista_vertices[i]+'-'+self.lista_vertices[j]))
                else:
                    matriz_a[i].append(0)
        self.matriz=matriz_a

    def imprimirMatriz(self, matriz):
        vertices=self.G.get_vertice()
        print(end='   ')
        for vertice in vertices:
            print(vertice, end=' ')
        print("\n")
        for i, vertice in zip(range(len(matriz)) ,vertices):
            print(vertice, end='  ')
            for j in range(len(matriz[i])):
                    print(matriz[i][j], end=" ")
            print("\n")

    #questão 3 letra A
    def nao_adjacentes(self):
        n_adjacentes=[]
        for i in range(len(self.matriz)):
            for j in range(len(self.matriz)):
                if (self.matriz[i][j]==0):
                    n_adjacentes.append(self.lista_vertices[i]+'-'+self.lista_vertices[j])
        return n_adjacentes

    # questão 3 letra B
    def adjacente_nele(self):
        for i in range(len(self.matriz)):
            for j in range(len(self.matriz)):
                if (self.matriz[i][j]==1 and i==j):
                    return True
        return False

    # questão 3 letra C
    def se_paralelas(self):
        for i in range(len(self.matriz)):
            for j in range(len(self.matriz)):
                if ((self.matriz[i][j]>1 or self.matriz[j][i]>1) and i!=j):
                    return True
                elif(self.matriz[i][j]>1 and i==j):
                    return True
        return False

    # questão 3 letra D
    def grau_vertice(self, vertice):
        indice=self.lista_vertices.index(vertice)
        grau=[0,0]
        for i in range(len(self.matriz)):
            grau[0]+=self.matriz[indice][i]
            grau[1]+=self.matriz[i][indice]
        return grau

    def grau_simples(self, vertice):
        indice = self.lista_vertices.index(vertice)
        grau = 0
        for i in range(len(self.matriz)):
            if i!=indice:
                grau += self.matriz[indice][i]
                grau += self.matriz[i][indice]
            else:
                grau += self.matriz[i][indice]
        return grau

    # questão 3 letra E
    def incidem_vertice(self, vertice):
        incide_vertice=[]
        indice=self.lista_vertices.index(vertice)
        for i in range(len(self.matriz)):
            if (self.matriz[i][indice]>0):
                incide_vertice.append(self.lista_vertices[i]+'-'+self.lista_vertices[indice])
            if (i!=indice and self.matriz[indice][i]>0):
                incide_vertice.append(self.lista_vertices[indice] + '-' + self.lista_vertices[i])

        return incide_vertice

    # questão 3 letra F
    def se_completo(self):
        cont=0
        for i in range(len(self.matriz)):
            for j in range(len(self.matriz)):
                if (i!=j and self.matriz[i][j]>0):
                    cont+=1
        referencia = (len(self.lista_vertices)**2)-len(self.lista_vertices)
        if (cont==referencia):
            return True
        else:
            return False

    #desafio letra i questão 3
    def percorrer_grafo(self, indice, a=[]):
        a.append(indice)
        for i in range(len(self.lista_vertices)):
            if (self.matriz[i][indice]>0 and self.matriz[indice][i]>0) and i not in a:
                self.percorrer_grafo(i, a)
        return a
    def se_conexo(self):
        a=[]
        cont=0
        for i in range(len(self.lista_vertices)):
            if (i not in a):
                cont+=1
                a = self.percorrer_grafo(i, a)
        if(cont>1):
            return False
        else:
            return True


    #algoritmo de euler
    # saber caminho euleriano
    def existe_caminhoEuleriano(self):
        total = 0
        for a in self.lista_vertices:
            grau=self.grau_simples(a)
            if grau%2==0:
                pass
            else:
                total+=1
        if total==0 or total==2 and self.se_conexo():
            return True
        else:
            return False

    #algoritmo de Warshall

    def algWarshall(self):


        matriz_warshall=[]
        for i in range(len(self.matriz)):
            matriz_warshall.append([])
            for j in range(len(self.matriz)):
                matriz_warshall[i].append(self.matriz[i][j])




        for i in range(len(self.matriz)):
            for j in range(len(self.matriz)):
                if matriz_warshall[j][i]>0:
                    for k in range(len(self.matriz)):
                        matriz_warshall[j][k] = max(matriz_warshall[j][k],matriz_warshall[i][k])

        self.imprimirMatriz(self.matriz)
        print("\n")

        self.imprimirMatriz(matriz_warshall)



