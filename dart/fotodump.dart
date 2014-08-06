import 'dart:html';

import "library/log.dart" as LOG;

void main() {
  LOG.debug("Starting...");
  var h1Element = new Element.html("<h1>Fotodump</h1>");
  document.body.children.add(h1Element);
}
