# Soap Web Services
Este é um curso para aplicar conceitos de SOAP.

## SOAP
SOAP foi anteriormente uma abreviação para Simple Object Access Protocol. No SOAP, a solicitação e a resposta estão no formato XML. No entanto, nem todos os tipos de XML são pedidos de SOAP válidos.

## REST x SOAP
REST vs SOAP não são realmente comparáveis. REST é um estilo arquitetônico. SOAP é um formato de troca de mensagens.

Comparando as implementações populares dos estilos REST e SOAP.

- Implementação da amostra RESTful : JSON sobre HTTP
- Implementação da amostra de SOAP : XML sobre SOAP sobre HTTP

## A seguir, as coisas importantes a considerar:
- Rest é construído sobre o simples protocolo HTTP. 
- Os serviços de SOAP são mais complexos de implementar e mais complexos de consumir.
- REST tem melhor desempenho e escalabilidade. 
- As leituras de DESCANSO podem ser armazenadas em cache, as leituras baseadas em SOAP não podem ser armazenadas em cache.
- Rest permite muitos formatos de dados diferentes (JSON é a escolha mais popular) onde como o SOAP só permite XML.
- Os serviços SOAP possuem estrutura e interface bem definidas (WSDL) e possuem um conjunto de padrões bem definidos (WS-Security, WS-AtomicTransaction e WS-ReliableMessaging). 
- As normas de documentação com REST estão evoluindo (usaremos o Swagger).

## Exemplos de serviços SOAP

Request
```
<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
        <getCourseDetailsRequest xmlns="http://in28minutes.com/courses">
            <id>Course1</id>
        </getCourseDetailsRequest>
    </Body>
</Envelope>
```
Response
```
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:getCourseDetailsResponse xmlns:ns2="http://in28minutes.com/courses">
            <ns2:course>
                <ns2:id>Course1</ns2:id>
                <ns2:name>Spring</ns2:name>
                <ns2:description>10 Steps</ns2:description>
            </ns2:course>
        </ns2:getCourseDetailsResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```
Fault
```
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <SOAP-ENV:Fault>
            <faultcode>SOAP-ENV:Server</faultcode>
            <faultstring xml:lang="en">java.lang.NullPointerException</faultstring>
        </SOAP-ENV:Fault>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```
WSDL
```
<?xml version="1.0" encoding="UTF-8" standalone="no"?><wsdl:definitions xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:sch="http://in28minutes.com/courses" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://in28minutes.com/courses" targetNamespace="http://in28minutes.com/courses">
  <wsdl:types>
    <xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema" elementFormDefault="qualified" targetNamespace="http://in28minutes.com/courses">

    <xs:element name="getCourseDetailsRequest">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="id" type="xs:string"/>
            </xs:sequence>
        </xs:complexType>
    </xs:element>

    <xs:element name="getCourseDetailsResponse">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="course" type="tns:course"/>
            </xs:sequence>
        </xs:complexType>
    </xs:element>

    <xs:complexType name="course">
        <xs:sequence>
            <xs:element name="id" type="xs:string"/>
            <xs:element name="name" type="xs:string"/>
            <xs:element name="description" type="xs:string"/>
        </xs:sequence>
    </xs:complexType>
</xs:schema>
  </wsdl:types>
  <wsdl:message name="getCourseDetailsRequest">
    <wsdl:part element="tns:getCourseDetailsRequest" name="getCourseDetailsRequest">
    </wsdl:part>
  </wsdl:message>
  <wsdl:message name="getCourseDetailsResponse">
    <wsdl:part element="tns:getCourseDetailsResponse" name="getCourseDetailsResponse">
    </wsdl:part>
  </wsdl:message>
  <wsdl:portType name="CoursesPort">
    <wsdl:operation name="getCourseDetails">
      <wsdl:input message="tns:getCourseDetailsRequest" name="getCourseDetailsRequest">
    </wsdl:input>
      <wsdl:output message="tns:getCourseDetailsResponse" name="getCourseDetailsResponse">
    </wsdl:output>
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="CoursesPortSoap11" type="tns:CoursesPort">
    <soap:binding style="document" transport="http://schemas.xmlsoap.org/soap/http"/>
    <wsdl:operation name="getCourseDetails">
      <soap:operation soapAction=""/>
      <wsdl:input name="getCourseDetailsRequest">
        <soap:body use="literal"/>
      </wsdl:input>
      <wsdl:output name="getCourseDetailsResponse">
        <soap:body use="literal"/>
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="CoursesPortService">
    <wsdl:port binding="tns:CoursesPortSoap11" name="CoursesPortSoap11">
      <soap:address location="http://localhost:8080/ws"/>
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>
```

