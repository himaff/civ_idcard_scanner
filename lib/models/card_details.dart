
import 'package:civ_idcard_scanner/utils.dart';

class CardDetails {
  String _cardNumber = "";
  String _cardIssuer = "";
  String _cardFirstName = "";
  String _cardLastName = "";
  String _expiryDate = "";

  CardDetails.fromMap(Map<String, String> map) {
    _cardNumber = map['cardNumber'] ?? '';
    _cardIssuer = CardUtils().getCardIssuer(_cardNumber).toString();
    _cardFirstName = map['cardFirstName'] ?? '';
    _cardLastName = map['cardLastName'] ?? '';
    _expiryDate = map['expiryDate'] ?? '';
  }

  Map<String, String> get map => {
        'cardNumber': _cardNumber,
        'cardIssuer': _cardIssuer,
        'cardFirstName': _cardFirstName,
        'cardLastName': _cardLastName,
        'expiryDate': _expiryDate,
      };

  @override
  String toString() {
    var string = '';
    string += _cardNumber.isEmpty ? "" : 'Card Number = $cardNumber\n';
    string += _expiryDate.isEmpty ? "" : 'Expiry Date = $expiryDate\n';
    string += _cardIssuer.isEmpty ? "" : 'Card Issuer = $cardIssuer\n';
    string += _cardFirstName.isEmpty ? "" : 'Card First Name = $cardFirstName\n';
    string += _cardLastName.isEmpty ? "" : 'Card Last Name = $cardLastName\n';
    return string;
  }

  String get cardNumber => _cardNumber;

  String get cardIssuer => _cardIssuer;

  String get cardFirstName => _cardFirstName;
  String get cardLastName => _cardLastName;

  String get expiryDate => _expiryDate;
}
