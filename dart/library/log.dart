library loglib;

debug(msg) => print("DEBUG: $msg");

warn(msg) => print("WARN: $msg");

info(msg) => print("INFO: $msg");

class Logger {
  log(msg) => print("LOG: $msg");
}