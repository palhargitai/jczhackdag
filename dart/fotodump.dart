import 'dart:html';
import 'dart:convert';
import 'package:crypto/crypto.dart';

import "library/log.dart" as LOG;

void main() {
  LOG.debug("Starting...");
  var h1Element = new Element.html("<h1>Fotodump</h1>");
  document.body.children.add(h1Element);

  var inputElement = new InputElement();
  inputElement.placeholder = "Naam foto";
  document.body.children.add(inputElement);

  var buttonElement = new ButtonElement();
  buttonElement.text = "Toon foto";
  document.body.children.add(buttonElement);

  DivElement divElement = new DivElement();
  divElement.id = "fotoDiv";
  document.body.children.add(divElement);

  buttonElement.onClick.listen((event) => ophalenFoto(inputElement));
}

ophalenFoto(InputElement inputElement) {
  String fotoNaam = inputElement.value;
  LOG.debug("Ophalen van foto ${fotoNaam}");

  DivElement divElement = querySelector("#fotoDiv");

  var url = "http://localhost/fotodump/${inputElement.value}/content";
  ImageElement img = new ImageElement();
  img.src = url;
  img.onClick.listen((event) => toonTagger(divElement, fotoNaam));


  divElement.children.add(img);
}


tagFoto(InputElement tagInputElement, String fotoNaam) {
  String tagNaam = tagInputElement.value;

  HttpRequest request = new HttpRequest(); // create a new XHR
  var url = "http://localhost/fotodump/tag/${fotoNaam}/${tagNaam}";
  request.open("PUT", url, async: false);
  request.send();
  tagInputElement.value = null;
}

toonTagger(DivElement divElement, String fotoNaam) {
  InputElement tagInputElement = new InputElement();
  tagInputElement.placeholder = "Tag";
  document.body.children.add(tagInputElement);
  divElement.children.add(tagInputElement);

  var tagButton = new ButtonElement();
  tagButton.text = "Tag!";
  divElement.children.add(tagButton);

  tagButton.onClick.listen((event) => tagFoto(tagInputElement, fotoNaam));
}

