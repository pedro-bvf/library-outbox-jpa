export function toIso(date = new Date(), timeZone = 'Europe/Madrid') {
    const parts = new Intl.DateTimeFormat('en-GB', {
        timeZone, year: 'numeric', month: '2-digit', day: '2-digit',
        hour: '2-digit', minute: '2-digit', second: '2-digit',
        fractionalSecondDigits: 3, hourCycle: 'h23', timeZoneName: 'longOffset'
    }).formatToParts(date);
    const get = (type) => parts.find(p => p.type === type).value;
    return `${get('year')}-${get('month')}-${get('day')}T${get('hour')}:${get('minute')}:${get('second')}.${get('fractionalSecond')}${get('timeZoneName').replace('GMT', '')}`;
}
