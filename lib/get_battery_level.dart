import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class BatteryScreen extends StatefulWidget {
  const BatteryScreen({super.key});

  @override
  State<BatteryScreen> createState() => _BatteryScreenState();
}

class _BatteryScreenState extends State<BatteryScreen> {
  static const batteryChannel = MethodChannel('com.example.battery_level_android_channel/battery');
  String? batteryLevel = '';

  @override
  Widget build(BuildContext context) {
    return  SafeArea(
      child: Scaffold(
        body: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [

            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(
                    batteryLevel??'',
                  style: TextStyle(
                    fontSize: 20,
                    color: Colors.green,
                    fontWeight: FontWeight.bold
                  ),
                ),
              ],
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                TextButton(
                    onPressed: (){
                      getbatteryLevel();
                    },
                    child: Text(
                        'Get Battery level'
                    )
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }

  Future<void> getbatteryLevel() async {

    final arguments = {'Hamza':'Hamza'};

    final int newbatteryLevel = await batteryChannel.invokeMethod('getBatteryLevel' , arguments);

    setState(() {
      batteryLevel = '$newbatteryLevel';
    });

  }
}
