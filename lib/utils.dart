import 'package:civ_idcard_scanner/civ_idcard_scanner.dart';

class CardUtils {
  final new_ci_cni = RegExp("CI([0-9]{9})");
  final old_ci_cni = RegExp("C([0-9]{10})");
  final unknown = RegExp('.*');

  CardIssuer getCardIssuer(String cardNumber) {
    cardNumber = cardNumber.replaceAll(' ', '');
    var issuerMap = <RegExp, CardIssuer>{
      old_ci_cni: CardIssuer.old_ci_cni,
      new_ci_cni: CardIssuer.new_ci_cni,
      unknown: CardIssuer.unknown
    };

    var matchingRegex = <RegExp>[
      new_ci_cni,
      old_ci_cni,
      unknown,
    ].firstWhere((element) => element.hasMatch(cardNumber));

    return issuerMap[matchingRegex]!;
  }
}
