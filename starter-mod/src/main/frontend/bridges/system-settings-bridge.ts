import { from, Observable } from 'rxjs';
import client from "./ConnectionFactory";
import { SystemSettingsOptions } from '../enums/SystemSettingsOptions';
import { map } from 'rxjs/operators';

const SERVICE = "system-settings-service";

/**
 * <h1>{@link SystemSettingsBridge}</h1>
 * @param option
 * @constructor
 */
export const SystemSettingsBridge = (option: SystemSettingsOptions): Observable<any> => {
  return from(
    client.call(SERVICE, "processSettings", { option })
  ).pipe(map(response => response));
};
