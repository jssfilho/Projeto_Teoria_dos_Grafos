from grafo import *


class Funcoes:
    def __init__(self, G: object):
        self.G = G

    # questao 3 letra a
    def nao_adjacente(self):
        v = self.G.get_vertice()
        n_ad = []
        for i in v:
            for j in v:
                if i != j and not self.G.existeAresta(i + '-' + j) and not self.G.existeAresta(j + '-' + i):
                    n_ad.append(i + '-' + j)
        if len(n_ad) == 0:
            print("O grafo é completo")
        return n_ad

    # questao 3 letra b
    def himself(self):
        v = self.G.get_vertice()
        for i in v:
            if self.G.existeAresta(i + '-' + i):
                return True
        return False

    # questao 3 letra c
    def a_paralelos(self):
        v = self.G.get_vertice()
        a = []
        for x in self.G.get_aresta().values():
            a.append(x)
        for i in v:
            for j in v:
                n = a.count(i + '-' + j) + a.count(j + '-' + i)
                if n > 1 and i!=j:
                    return True
        return False

    # questao 3 letra d
    def grau_vertece(self, b):
        cont = 0
        v = self.G.get_vertice()
        for i in v:
            if self.G.existeAresta(b + '-' + i) or self.G.existeAresta(i + '-' + b):
                cont += 1
        return cont

    # questao 3 letra e
    def mostrar_arestas(self, b):
        lista_arestas = []
        v = self.G.get_vertice()
        for i in v:
            if self.G.existeAresta(b + '-' + i) or self.G.existeAresta(i + '-' + b):
                lista_arestas.append(b + '-' + i)
        return lista_arestas

    # questao 3 letra f
    def verifica_completo(self):
        v = self.G.get_vertice()
        cont = 0
        for i in v:
            if self.grau_vertece(i)==len(v)-1 and not self.G.existeAresta(i+'-'+i):
                cont += 1
            elif (self.grau_vertece(i)-1)==len(v)-1 and self.G.existeAresta(i+'-'+i):
                cont += 1
        if cont == len(v):
            return True
        else:
            return False

    # Função para uso geral
    def percorrer_grafo(self,b,a=[]):
        v = self.G.get_vertice()
        a.append(b)
        for i in v:
            n=(self.G.existeAresta(i + '-' + b) or self.G.existeAresta(b + '-' + i))

            if(n and (i not in a)):
                self.percorrer_grafo(i, a)
        return a


    #(DESAFIO) LETRA G QUESTÃO 3 (desevolvendo ...)
    def existeCiclo(self):
        v=self.G.get_vertice()
        for i in v:
            a=[]
            if i not in a:
                a = self.percorrer_grafo(i,a)
    #(DESAFIO) LETRA H QUESTÃO 3 (tentando...)
    def percorrer_caminho(self,b,a=[]):
        v = self.G.get_vertice()
        a.append(b)
        for i in v:
            n=(self.G.existeAresta(i + '-' + b) or self.G.existeAresta(b + '-' + i))
            if(n and (i not in a)):
                return self.percorrer_grafo(i, a)
        return a
    def tam_caminho(self, t):
        maior=0
        v = self.G.get_vertice()
        a=[]
        for j in v:
            a = self.percorrer_caminho(j, a)
            if len(a)-1>=maior:
                maior=len(a)-1
            a=[]
        if(maior>=t):
            return True
        else:
            return False

    #(DESAFIO) LETRA I QUESTÃO 3
    def verifica_conexo(self):
        a = []
        v = self.G.get_vertice()
        cont=0
        for j in v:
            if j not in a:
                cont+=1
                a = self.percorrer_grafo(j,a)
        if cont>=2:
            return False
        else:
            return True

