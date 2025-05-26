

````markdown
# Sistema de Reembolso de Plano de Saúde

Este projeto simula o cálculo de reembolso de consultas médicas em um plano de saúde. Ele foi construído com foco em boas práticas de orientação a objetos, como injeção de dependência, uso de interfaces e aplicação de testes com stubs, mocks e spies.

## 📌 Objetivo

- Calcular o valor de reembolso de uma consulta médica.
- Considerar o percentual de cobertura do plano de saúde.
- Validar autorização do reembolso.
- Aplicar teto máximo quando necessário.
- Registrar auditorias das operações.

---

## 🧱 Estrutura do Projeto

### Classes e Interfaces Principais

#### ✅ `Paciente`
Representa o paciente que realizou a consulta.

- **Atributos:** Nome, CPF
- **Uso:** Identificação do paciente em diversas operações.

#### ✅ `Consulta`
Representa a consulta médica.

- **Uso:** Usada para determinar o valor da consulta que será reembolsado.

#### ✅ `PlanoSaude` (interface)
Interface que define regras do plano de saúde.

- **Métodos:**
  - `double valorReembolsavel(Consulta consulta)`
  - `double getPercentualDeCobertura(Paciente paciente)`
- **Uso:** Permite múltiplas implementações com diferentes regras de cobertura.

#### ✅ `AutorizadorReembolso` (interface)
Interface para validar se um reembolso pode ser feito.

- **Métodos:**
  - `boolean autorizar(Paciente paciente, double valorConsulta)`
  - `boolean estaAutorizado(Reembolso reembolso)`
- **Uso:** Controla lógica de autorização com base em regras específicas.

#### ✅ `Auditoria` (interface)
Interface usada para registrar auditorias das operações de reembolso.

- **Método:**
  - `void registrarConsulta(Paciente paciente, double valorConsulta, double valorReembolso)`
- **Uso:** Garante rastreabilidade dos reembolsos realizados.

#### ✅ `AuditoriaSpy`
Implementação "Spy" para verificar se o método da auditoria foi realmente chamado durante os testes.

- **Uso:** Usada em testes unitários.

#### ✅ `Reembolso`
Representa um reembolso aprovado ou em análise.

- **Uso:** Armazena os valores da operação para validação posterior.

#### ✅ `CalculadoraDeReembolso`
Classe central que executa o cálculo do valor de reembolso.

- **Construtor:**
  ```java
  public CalculadoraDeReembolso(PlanoSaude plano, Auditoria auditoria, AutorizadorReembolso autorizador)
````

* **Método:**

  ```java
  public double calcularReembolso(double valorConsulta, double percentualCobertura, Paciente paciente)
  ```
* **Responsabilidade:** Aplica todas as regras (cobertura, autorização, auditoria) e retorna o valor final a ser reembolsado.

#### ✅ `AplicadorDeTeto`

Classe que define um valor máximo que pode ser reembolsado.

* **Uso:** Aplica restrições sobre o valor final de reembolso.

---

## 🧪 Testes

Foram utilizados **Stubs, Mocks e Spies** para garantir testabilidade e independência das classes.

### Exemplos de Testes

#### 🔹 `AuditoriaSpyTest`

Testa se o método `registrarConsulta()` foi chamado ao calcular o reembolso:

```java
AuditoriaSpy auditoriaSpy = new AuditoriaSpy();
assertTrue(auditoriaSpy.foiChamado());
```

#### 🔹 Teste com Stub de `PlanoSaude` e `AutorizadorReembolso`

```java
PlanoSaude planoStub = new PlanoSaude() {
    public double valorReembolsavel(Consulta consulta) {
        return 250.0;
    }
    public double getPercentualDeCobertura(Paciente paciente) {
        return 0.8;
    }
};
```

---

## ✅ Por que usar Interfaces?

* **Flexibilidade:** Permite trocar implementações sem alterar a lógica principal.
* **Testabilidade:** Com interfaces, podemos criar stubs e mocks para testar de forma isolada.
* **Responsabilidade única:** Cada classe trata de um aspecto diferente (autorização, auditoria, plano).

---

## 📦 Exemplo de Fluxo de Cálculo

1. Recebe o valor da consulta e paciente.
2. Busca o percentual de cobertura do plano.
3. Verifica se a operação está autorizada.
4. Aplica o valor percentual sobre a consulta.
5. Registra a operação na auditoria.
6. Retorna o valor reembolsável final.

---

## 🔧 Tecnologias e Ferramentas

* Java 17+
* JUnit 5
* IDE: IntelliJ IDEA (recomendado)
* Paradigma: Programação Orientada a Objetos
* Testes com Mocks, Stubs e Spies

---

## 🧠 Aprendizados

* Como isolar responsabilidades usando interfaces.
* A importância de testes unitários com dependências simuladas.
* Como simular comportamento com Spies para validação de chamadas.
* Separação de lógica de negócios em componentes reutilizáveis.

---

## 📁 Estrutura de Pastas

```
src/
 └── main/
     └── java/
         └── org/example/ReembolsoBasico/
             ├── Paciente.java
             ├── Consulta.java
             ├── Reembolso.java
             ├── CalculadoraDeReembolso.java
             ├── Auditoria.java
             ├── PlanoSaude.java
             └── AutorizadorReembolso.java

 └── test/
     └── java/
         └── reembolsoBasico/
             ├── AuditoriaSpyTest.java
             └── AplicadorDeTetoComStubEMockValidacaoCompletoTest.java
```

---

## ✍️ Autor

* **Weslley Soares**
* Projeto acadêmico de domínio: DR3\_TP2

